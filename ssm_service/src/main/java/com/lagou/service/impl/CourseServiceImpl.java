package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        return list;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        // 封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVO);

        // 补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        // 保存课程信息
        courseMapper.saveCourse(course);

        // 获取新插入数据的id值
        int courseId = course.getId();

        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVO);

        // 补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(courseId);

        // 保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    /**
     * 更新课程信息和讲师信息
     */
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        // 封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVO);

        // 补全课程信息
        Date date = new Date();
        course.setUpdateTime(date);

        // 更新课程信息
        courseMapper.updateCourse(course);

        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVO);

        // 补全讲师信息
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());

        // 更新讲师信息
        courseMapper.updateTeacher(teacher);
    }

    /**
     * 课程状态管理
     */
    @Override
    public void updateCourseStatus(int id, int status) {

        // 封装数据
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        // 调用dao
        courseMapper.updateCourseStatus(course);
    }
}
