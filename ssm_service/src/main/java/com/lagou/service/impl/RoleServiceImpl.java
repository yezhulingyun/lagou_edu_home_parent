package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String operator = roleMenuVO.getUser() == null ? "system" : roleMenuVO.getUser().getName();
        // 为角色分配菜单
        for (Integer mid : roleMenuVO.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            // 封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy(operator);
            role_menu_relation.setUpdatedBy(operator);
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    /**
     * 添加角色
     */
    @Override
    public void saveRole(Role role, User user) {
        // 1. 补全信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        String operator = user == null ? "system" : user.getName();
        role.setCreatedBy(operator);
        role.setUpdatedBy(operator);
        // 2. 调用mapper
        roleMapper.saveRole(role);
    }

    /**
     * 修改角色
     */
    @Override
    public void updateRole(Role role, User user) {
        // 1. 补全信息
        String operator = user == null ? "system" : user.getName();
        role.setUpdatedBy(operator);
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

    /**
     * 根据角色id获取当前角色拥有的资源信息
     */
    @Override
    public List<ResourceCategory> findResourceListByRoleId(int roleId) {
        // 获取当前角色拥有的资源分类集合
        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryByRoleId(roleId);
        // 将资源分类集合中的所有分类对象，以id为key、分类对象resourceCategory为value，存入map集合
        Map<Integer, ResourceCategory> categoryMap = new HashMap<>();
        for (ResourceCategory resourceCategory : resourceCategoryList) {
            categoryMap.put(resourceCategory.getId(), resourceCategory);
        }
        // 获取当前角色拥有的资源信息集合
        List<Resource> resourceList = roleMapper.findResourceByRoleId(roleId);
        // 根据资源对象的categoryId属性，放入对应的资源分类下
        for (Resource resource : resourceList) {
            ResourceCategory resourceCategory = categoryMap.get(resource.getCategoryId());
            resourceCategory.getResourceList().add(resource);
        }
        // 返回资源分类集合
        return resourceCategoryList;
    }

    /**
     * 为角色分配资源信息
     */
    @Override
    public void roleContextResource(RoleResourceVO roleResourceVO) {
        // 1. 根据角色id清空中间表中当前角色关联的资源信息
        roleMapper.deleteRoleContextResource(roleResourceVO.getRoleId());
        User user = roleResourceVO.getUser();
        String operator = user == null ? "system" : user.getName();
        // 2. 重新在中间表中添加当前角色关联的资源信息
        for (Integer ri : roleResourceVO.getResourceIdList()) {
            // 封装数据
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setRoleId(roleResourceVO.getRoleId());
            roleResourceRelation.setResourceId(ri);
            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy(operator);
            roleResourceRelation.setUpdatedBy(operator);
            // 调用mapper
            roleMapper.roleContextResource(roleResourceRelation);
        }
    }
}
