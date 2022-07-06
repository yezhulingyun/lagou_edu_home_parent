package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

public interface ResourceService {

    /**
     * 分页&多条件查询资源信息
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);

    /**
     * 添加资源信息
     */
    public void saveResource(Resource resource);

    /**
     * 修改资源信息
     */
    public void updateResource(Resource resource);

    /**
     * 删除资源信息
     */
    public void deleteResource(int id);
}
