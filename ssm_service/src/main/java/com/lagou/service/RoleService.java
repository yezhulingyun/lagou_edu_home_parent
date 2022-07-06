package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.RoleResourceVO;

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
     * 添加角色
     */
    public void saveRole(Role role);

    /**
     * 修改角色
     */
    public void updateRole(Role role);

    /**
     * 删除角色
     */
    public void deleteRole(int id);

    /**
     * 根据角色id获取当前角色拥有的资源信息
     */
    public List<ResourceCategory> findResourceListByRoleId(int roleId);

    /**
     * 为角色分配资源信息
     */
    public void roleContextResource(RoleResourceVO roleResourceVO);
}
