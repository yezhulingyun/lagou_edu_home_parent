package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.User;

import java.util.List;

public interface ResourceCategoryService {

    /**
     * 查询所有的资源分类信息
     */
    public List<ResourceCategory> findAllResourceCategory();

    /**
     * 添加资源分类
     */
    public void saveResourceCategory(ResourceCategory resourceCategory, User user);

    /**
     * 修改资源分类
     */
    public void updateResourceCategory(ResourceCategory resourceCategory, User user);

    /**
     * 删除资源分类
     */
    public void deleteResourceCategory(int id);
}
