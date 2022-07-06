package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询角色列表（条件）
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询关联的菜单id
     */
    public List<Integer> findMenuByRoleId(int roleId);

    /**
     * 根据角色id从中间表中删除角色关联的菜单信息
     */
    public void deleteRoleContextMenu(int roleId);

    /**
     * 为角色分配菜单
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

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
     * 根据角色id查询关联的资源分类信息
     */
    public List<ResourceCategory> findResourceCategoryByRoleId(int roleId);

    /**
     * 根据角色id查询关联的资源信息
     */
    public List<Resource> findResourceByRoleId(int roleId);

    /**
     * 根据角色id从中间表中删除角色关联的资源信息
     */
    public void deleteRoleContextResource(int roleId);

    /**
     * 为角色分配资源
     */
    public void roleContextResource(RoleResourceRelation roleResourceRelation);
}
