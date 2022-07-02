package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户分页&多条件查询
     */
    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    /**
     * 修改用户状态
     */
    @Override
    public void updateUserStatus(int id, String status) {

        // 1.封装数据
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(new Date());

        // 2.调用mapper
        userMapper.updateUserStatus(user);
    }
}
