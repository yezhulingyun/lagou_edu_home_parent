package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户分页&多条件查询
     */
    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    /**
     * 修改用户状态
     */
    @Override
    public void updateUserStatus(int id, String status) {

        // 1.封装数据
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(new Date());

        // 2.调用mapper
        userMapper.updateUserStatus(user);
    }

    /**
     * 用户登录
     */
    @Override
    public User login(User user) throws Exception {
        User user2 = userMapper.login(user);
        if (user2 != null && Md5.verify(user.getPassword(), "lagou", user2.getPassword())) {
            return user2;
        } else {
            return null;
        }
    }

    /**
     * 用户注册
     */
    @Override
    public void register(User user) throws Exception {
        String encodeStr = Md5.md5(user.getPassword(), "lagou");
        user.setPassword(encodeStr);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.register(user);
    }

    /**
     * 根据用户id查询该用户关联的角色信息
     */
    @Override
    public List<Role> findUserRoleById(int id) {
        List<Role> roleList = userMapper.findUserRoleById(id);
        return roleList;
    }

    /**
     * 为用户分配角色
     */
    @Override
    public void userContextRole(UserVO userVO) {
        // 1.根据用户id清空中间表中的用户与角色的关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        // 2.重新在中间表中建立起该用户与角色的关联关系
        for (Integer ri : userVO.getRoleIdList()) {
            // 封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(ri);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy(userVO.getUsername());
            user_role_relation.setUpdatedBy(userVO.getUsername());

            // 调用mapper
            userMapper.userContextRole(user_role_relation);
        }
    }

    /**
     * 获取用户拥有的权限，进行菜单的动态展示
     */
    @Override
    public Map<String, Object> getUserPermissions(int userId) {
        // 1. 根据用户id查询关联的角色信息
        Map<String, Object> map = new HashMap<>();
        List<Role> roleList = userMapper.findUserRoleById(userId);
        if (roleList.size() == 0) {
            map.put("menuList", null);
            map.put("resourceList", null);
            return map;
        }
        // 2. 把查询出的角色的id封装到集合中
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        // 3. 根据角色id查询关联的父级菜单信息
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(roleIds);
        // 4. 根据父级菜单的id查询子级菜单信息
        for (Menu menu : parentMenuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuList);
        }
        // 5. 根据角色id查询关联的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        // 6. 封装数据并返回
        map.put("menuList", parentMenuList);
        map.put("resourceList", resourceList);

        return map;
    }
}
