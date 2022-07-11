package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;
import com.lagou.domain.User;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有的父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询所有菜单信息
     */
    public PageInfo<Menu> findAllMenu(MenuVO menuVO);

    /**
     * 根据id查询菜单信息
     */
    public Menu findMenuById(int id);

    /**
     * 添加菜单信息
     */
    public void saveMenu(Menu menu, User user);

    /**
     * 修改菜单信息
     */
    public void updateMenu(Menu menu, User user);
}
