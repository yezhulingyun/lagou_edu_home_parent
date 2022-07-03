package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /**
     * 查询所有的资源分类信息
     */
    public List<ResourceCategory> findAllResourceCategory();
}
