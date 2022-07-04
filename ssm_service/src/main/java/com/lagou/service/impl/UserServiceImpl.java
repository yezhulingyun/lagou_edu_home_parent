package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.domain.User_Role_relation;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedBy("system");

            // 调用mapper
            userMapper.userContextRole(user_role_relation);
        }
    }
}
