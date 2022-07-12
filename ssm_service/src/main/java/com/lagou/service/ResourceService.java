package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.User;

public interface ResourceService {

    /**
     * 分页&多条件查询资源信息
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);

    /**
     * 添加资源信息
     */
    public void saveResource(Resource resource, User user);

    /**
     * 修改资源信息
     */
    public void updateResource(Resource resource, User user);

    /**
     * 删除资源信息
     */
    public void deleteResource(int id);
}
