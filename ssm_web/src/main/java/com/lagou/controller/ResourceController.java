package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.User;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 分页&多条件查询资源信息
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVO resourceVO) {
        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVO);
        return new ResponseResult(true, 200, "资源信息分页多条件查询成功", pageInfo);
    }

    /**
     * 添加&修改资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 判断id是否为空
        if (resource.getId() == null) {
            // 添加操作
            resourceService.saveResource(resource, user);
            return new ResponseResult(true, 200, "添加资源信息成功", null);
        } else {
            // 修改操作
            resourceService.updateResource(resource, user);
            return new ResponseResult(true, 200, "修改资源信息成功", null);
        }
    }

    /**
     * 删除资源信息
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(int id) {
        resourceService.deleteResource(id);
        return new ResponseResult(true, 200, "删除资源信息成功", null);
    }
}
