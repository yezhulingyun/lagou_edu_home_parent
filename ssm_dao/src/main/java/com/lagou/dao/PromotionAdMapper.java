package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
     * 广告分页查询
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /**
     * 新增广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 广告状态上下线
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
