package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表（条件）
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> roleList = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "查询角色成功", roleList);
    }


    @Autowired
    private MenuService menuService;

    /**
     * 查询所有的父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);
        return new ResponseResult(true, 200, "查询所有的父子级菜单成功", map);
    }

    /**
     * 根据角色id查询关联的菜单id
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int roleId) {
        List<Integer> list = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "根据角色ID查询关联菜单ID成功", list);
    }

    /**
     * 为角色分配菜单信息
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        roleMenuVO.setUser(user);
        roleService.roleContextMenu(roleMenuVO);
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /**
     * 添加&修改角色
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 判断id是否为空
        if (role.getId() == null) {
            // 添加操作
            roleService.saveRole(role, user);
            return new ResponseResult(true, 200, "添加角色成功", null);
        } else {
            // 修改操作
            roleService.updateRole(role, user);
            return new ResponseResult(true, 200, "修改角色成功", null);
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id) {
        roleService.deleteRole(id);
        return new ResponseResult(true, 200, "删除角色成功", null);
    }

    /**
     * 根据角色id获取当前角色拥有的资源信息
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(int roleId) {
        List<ResourceCategory> categoryList = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true, 200, "获取当前角色拥有的资源信息成功", categoryList);
    }

    /**
     * 为角色分配资源信息
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVO roleResourceVO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        roleResourceVO.setUser(user);
        roleService.roleContextResource(roleResourceVO);
        return new ResponseResult(true, 200, "响应成功", null);
    }
}
