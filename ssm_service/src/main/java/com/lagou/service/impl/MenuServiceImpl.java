package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有的父子菜单信息
     */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);
        return menuList;
    }

    /**
     * 查询所有菜单信息
     */
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    /**
     * 根据id查询菜单信息
     */
    @Override
    public Menu findMenuById(int id) {
        return menuMapper.findMenuById(id);
    }

    /**
     * 添加菜单信息
     */
    @Override
    public void saveMenu(Menu menu) {
        // 1. 补全信息
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        // 2. 调用mapper
        menuMapper.saveMenu(menu);
    }

    /**
     * 修改菜单信息
     */
    @Override
    public void updateMenu(Menu menu) {
        // 1. 补全信息
        menu.setUpdatedTime(new Date());
        // 2. 调用mapper
        menuMapper.updateMenu(menu);
    }
}
