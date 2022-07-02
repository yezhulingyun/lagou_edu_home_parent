package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 根据课程id查询课程内容（章节+课时）
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(int courseId) {

        // 调用service
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

        // 封装数据并返回
        ResponseResult responseResult = new ResponseResult(true, 200, "查询章节和课时成功", sectionList);
        return responseResult;
    }

    /**
     * 根据课程id回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);
        return responseResult;
    }

    /**
     * 新增&修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {

        if (section.getId() == null) {
            // 新增
            courseContentService.saveSection(section);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增章节成功", null);
            return responseResult;
        } else {
            // 修改
            courseContentService.updateSection(section);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改章节成功", null);
            return responseResult;
        }
    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {
        courseContentService.updateSectionStatus(id, status);
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;
    }

    /**
     * 新增课时信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson lesson) {
        courseContentService.saveLesson(lesson);
        ResponseResult responseResult = new ResponseResult(true, 200, "新增课时信息成功", null);
        return responseResult;
    }
}