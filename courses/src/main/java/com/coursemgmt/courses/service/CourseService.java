package com.coursemgmt.courses.service;

import com.coursemgmt.courses.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course saveCourse(Course course);
    Course getCourseById(long id);
    long deleteCourseById(long id);
}
