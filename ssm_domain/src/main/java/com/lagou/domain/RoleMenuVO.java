package com.lagou.domain;

import java.util.List;

public class RoleMenuVO {

    private Integer roleId;
    private List<Integer> menuIdList;
    private User user;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RoleMenuVO{" +
                "roleId=" + roleId +
                ", menuIdList=" + menuIdList +
                '}';
    }
}
