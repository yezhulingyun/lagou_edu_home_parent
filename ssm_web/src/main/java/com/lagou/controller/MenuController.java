package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true, 200, "查询所有菜单信息成功", allMenu);
    }

    /**
     * 回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(int id) {

        // 根据id值判断是添加操作还是修改操作 判断id是否为-1
        if (id == -1) {
            // 添加操作 只查询父菜单，不回显具体的菜单信息
            List<Menu> parentMenuList = menuService.findSubMenuListByPid(-1);

            // 封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", parentMenuList);
            return new ResponseResult(true, 200, "添加操作回显信息成功", map);
        } else {
            // 修改操作 需要查询具体的menu信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> parentMenuList = menuService.findSubMenuListByPid(-1);

            // 封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", parentMenuList);
            return new ResponseResult(true, 200, "修改操作回显信息成功", map);
        }
    }
}
