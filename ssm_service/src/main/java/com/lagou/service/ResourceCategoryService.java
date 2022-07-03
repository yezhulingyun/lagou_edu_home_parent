package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {

    /**
     * 查询所有的资源分类信息
     */
    public List<ResourceCategory> findAllResourceCategory();
}
