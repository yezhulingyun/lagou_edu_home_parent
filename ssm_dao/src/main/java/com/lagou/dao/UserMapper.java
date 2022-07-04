package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.domain.User_Role_relation;

import java.util.List;

public interface UserMapper {

    /**
     * 用户分页&多条件查询
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(User user);

    /**
     * 用户登录（根据手机号查询用户信息）
     */
    public User login(User user);

    /**
     * 用户注册
     */
    public void register(User user);

    /**
     * 根据用户id查询该用户关联的角色信息
     */
    public List<Role> findUserRoleById(int id);

    /**
     * 根据用户id清空中间表用户与角色的关联信息
     */
    public void deleteUserContextRole(int userId);

    /**
     * 为用户分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);
}
