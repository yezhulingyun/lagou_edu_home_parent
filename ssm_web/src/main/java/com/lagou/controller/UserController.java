package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户分页&多条件查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true, 200, "分页多条件查询成功", pageInfo);
    }

    /**
     * 修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id, String status) {
        userService.updateUserStatus(id, status);
        return new ResponseResult(true, 200, "修改用户状态成功", status);
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user2 = userService.login(user);
        if (user2 != null) {
            // 保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            System.out.println(access_token);
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user2.getId());

            // 将查询出来的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id", user2.getId());
            // 将查询出来的user也存到map中
            map.put("user", user2);
            return new ResponseResult(true, 1, "登陆成功", map);
        } else {
            return new ResponseResult(true, 400, "用户名或密码错误", null);
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public ResponseResult register(@RequestBody User user) throws Exception {
        userService.register(user);
        return new ResponseResult(true, 200, "用户注册成功", null);
    }

    /**
     * 分配角色（回显）：根据用户id查询该用户关联的角色信息
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id) {
        List<Role> roleList = userService.findUserRoleById(id);
        return new ResponseResult(true, 200, "分配角色回显成功", roleList);
    }

    /**
     * 为用户分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO) {
        userService.userContextRole(userVO);
        return new ResponseResult(true, 200, "分配角色成功", null);
    }
}
