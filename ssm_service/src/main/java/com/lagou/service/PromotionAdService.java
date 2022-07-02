package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    /**
     * 广告分页查询
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

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
    public void updatePromotionAdStatus(int id, int status);

}
