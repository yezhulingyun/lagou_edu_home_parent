package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /**
     * 根据课程id查询课程内容（章节+课时）
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 根据课程id回显章节对应的课程信息
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 新增章节信息
     */
    public void saveSection(CourseSection section);

    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(int id, int status);

    /**
     * 新增课时信息
     */
    public void saveLesson(CourseLesson lesson);

    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson lesson);

    /**
     * 修改课时状态
     */
    public void updateLessonStatus(int id, int status);
}
