package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    /**
     * 广告分页查询
     */
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageHelper.startPage(promotionAdVO.getCurrentPage(), promotionAdVO.getPageSize());
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);
        return pageInfo;
    }

    /**
     * 新增广告
     */
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        // 1.补全数据
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        // 2.调用mapper
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    /**
     * 修改广告
     */
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        // 1.补全数据
        promotionAd.setUpdateTime(new Date());

        // 2.调用mapper
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    /**
     * 根据id查询广告信息
     */
    @Override
    public PromotionAd findPromotionAdById(int id) {
        return promotionAdMapper.findPromotionAdById(id);
    }

    /**
     * 广告状态上下线
     */
    @Override
    public void updatePromotionAdStatus(int id, int status) {

        // 1.封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        // 2.调用mapper
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
