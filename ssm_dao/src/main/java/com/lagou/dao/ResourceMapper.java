package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    /**
     * 分页&多条件查询资源信息
     */
    public List<Resource> findAllResourceByPage(ResourceVO resourceVO);
}
