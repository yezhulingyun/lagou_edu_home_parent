package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;

public interface UserService {

    /**
     * 用户分页&多条件查询
     */
    public PageInfo<User> findAllUserByPage(UserVO userVO);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(int id, String status);

    /**
     * 用户登录
     */
    public User login(User user) throws Exception;

    /**
     * 用户注册
     */
    public void register(User user) throws Exception;

    /**
     * 根据用户id查询该用户关联的角色信息
     */
    public List<Role> findUserRoleById(int id);

    /**
     * 为用户分配角色
     */
    public void userContextRole(UserVO userVO);
}
