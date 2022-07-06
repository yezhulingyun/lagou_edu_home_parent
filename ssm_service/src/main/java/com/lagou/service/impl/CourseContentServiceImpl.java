package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    /**
     * 根据课程id查询课程内容（章节+课时）
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionList;
    }

    /**
     * 根据课程id回显章节对应的课程信息
     */
    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /**
     * 新增章节信息
     */
    @Override
    public void saveSection(CourseSection section) {

        // 补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        // 调用dao
        courseContentMapper.saveSection(section);
    }

    /**
     * 修改章节信息
     */
    @Override
    public void updateSection(CourseSection section) {

        // 1.补全信息
        section.setUpdateTime(new Date());

        // 2.调用courseContentMapper方法
        courseContentMapper.updateSection(section);
    }

    /**
     * 修改章节状态
     */
    @Override
    public void updateSectionStatus(int id, int status) {

        // 1.封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());

        // 2.调用mapper
        courseContentMapper.updateSectionStatus(section);
    }

    /**
     * 新增课时信息
     */
    @Override
    public void saveLesson(CourseLesson lesson) {

        // 1.补全信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);

        // 2.调用mapper
        courseContentMapper.saveLesson(lesson);
    }

    /**
     * 修改课时信息
     */
    @Override
    public void updateLesson(CourseLesson lesson) {
        // 1. 补全信息
        lesson.setUpdateTime(new Date());
        // 2. 调用mapper
        courseContentMapper.updateLesson(lesson);
    }
}
