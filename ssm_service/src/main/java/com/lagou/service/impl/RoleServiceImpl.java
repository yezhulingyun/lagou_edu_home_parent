package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
