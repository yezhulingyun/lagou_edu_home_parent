package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色列表（条件）
     */
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    /**
     * 根据角色id查询关联的菜单id
     */
    @Override
    public List<Integer> findMenuByRoleId(int roleId) {
        List<Integer> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    /**
     * 为角色分配菜单信息
     */
    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        // 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        // 为角色分配菜单
        for (Integer mid : roleMenuVO.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            // 封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedBy("system");
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    /**
     * 添加角色
     */
    @Override
    public void saveRole(Role role) {
        // 1. 补全信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        // 2. 调用mapper
        roleMapper.saveRole(role);
    }

    /**
     * 修改角色
     */
    @Override
    public void updateRole(Role role) {
        // 1. 补全信息
        role.setUpdatedTime(new Date());
        // 2. 调用mapper
        roleMapper.updateRole(role);
    }

    /**
     * 删除角色
     */
    @Override
    public void deleteRole(int id) {
        // 根据角色id清空中间表中角色和菜单的关联关系
        roleMapper.deleteRoleContextMenu(id);
        // 删除角色
        roleMapper.deleteRole(id);
    }
}
