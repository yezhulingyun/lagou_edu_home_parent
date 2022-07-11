package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;
import com.lagou.domain.User;
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
    public PageInfo<Menu> findAllMenu(MenuVO menuVO) {
        PageHelper.startPage(menuVO.getCurrentPage(), menuVO.getPageSize());
        List<Menu> allMenu = menuMapper.findAllMenu();
        PageInfo<Menu> pageInfo = new PageInfo<>(allMenu);
        return pageInfo;
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
    public void saveMenu(Menu menu, User user) {
        // 1. 补全信息
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        String operator = user == null ? "system" : user.getName();
        menu.setCreatedBy(operator);
        menu.setUpdatedBy(operator);
        // 2. 调用mapper
        menuMapper.saveMenu(menu);
    }

    /**
     * 修改菜单信息
     */
    @Override
    public void updateMenu(Menu menu, User user) {
        // 1. 补全信息
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy(user == null ? "system" : user.getName());
        // 2. 调用mapper
        menuMapper.updateMenu(menu);
    }
}
