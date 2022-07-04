package com.lagou.dao;

import com.lagou.domain.User;
import com.lagou.domain.UserVO;

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
}
