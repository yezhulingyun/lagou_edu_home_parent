package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import com.lagou.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        return new ResponseResult(true, 200, "广告分页查询成功", pageInfo);
    }

    /**
     * 广告图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult promotionAdUpload (@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        // 调用图片上传工具类
        Map<String, String> map = FileUploadUtils.fileUpload(file, request);

        return new ResponseResult(true, 200, "图片上传成功", map);
    }

    /**
     * 新增&修改广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd (@RequestBody PromotionAd promotionAd) {

        if (promotionAd.getId() == null) {
            // 新增
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "新增广告成功", null);
        } else {
            // 修改
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "修改广告成功", null);
        }
    }

    /**
     * 修改页面回显广告信息
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id) {
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "查询广告信息成功", promotionAd);
    }

    /**
     * 广告状态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id, int status) {
        promotionAdService.updatePromotionAdStatus(id, status);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "广告上下线成功", map);
    }
}
