package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.User;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /**
     * 查询所有的资源分类信息
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    /**
     * 添加资源分类
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory, User user) {
        // 补全信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        String operator = user == null ? "system" : user.getName();
        resourceCategory.setCreatedBy(operator);
        resourceCategory.setUpdatedBy(operator);
        // 调用mapper
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    /**
     * 修改资源分类
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory, User user) {
        // 补全信息
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setUpdatedBy(user == null ? "system" : user.getName());
        // 调用mapper
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /**
     * 删除资源分类
     */
    @Override
    public void deleteResourceCategory(int id) {
        resourceCategoryMapper.deleteResourceCategory(id);
    }
}
