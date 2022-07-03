package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;

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

    /**
     * 为角色分配菜单信息
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /**
     * 删除角色
     */
    public void deleteRole(int id);
}
