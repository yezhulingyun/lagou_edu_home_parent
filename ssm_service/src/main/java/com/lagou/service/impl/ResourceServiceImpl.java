package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.User;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 分页&多条件查询资源信息
     */
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO) {
        PageHelper.startPage(resourceVO.getCurrentPage(), resourceVO.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResourceByPage(resourceVO);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        return pageInfo;
    }

    /**
     * 添加资源信息
     */
    @Override
    public void saveResource(Resource resource, User user) {
        // 1. 补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        String operator = user == null ? "system" : user.getName();
        resource.setCreatedBy(operator);
        resource.setUpdatedBy(operator);
        // 2. 调用mapper
        resourceMapper.saveResource(resource);
    }

    /**
     * 修改资源信息
     */
    @Override
    public void updateResource(Resource resource, User user) {
        // 1. 补全信息
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy(user == null ? "system" : user.getName());
        // 2. 调用mapper
        resourceMapper.updateResource(resource);
    }

    /**
     * 删除资源信息
     */
    @Override
    public void deleteResource(int id) {
        resourceMapper.deleteResource(id);
    }
}
