package com.lagou.service;

import com.lagou.domain.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询角色列表（条件）
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询关联的菜单id
     */
    public List<Integer> findMenuByRoleId(int roleId);
}
