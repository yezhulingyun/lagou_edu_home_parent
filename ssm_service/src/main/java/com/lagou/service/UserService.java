package com.lagou.service;

import com.github.pagehelper.PageInfo;
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
}
