package com.coursemgmt.courses.service;

import com.coursemgmt.courses.model.Course;
import com.coursemgmt.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        this.courseRepository.save(course);
        return course;
    }

    @Override
    public Course getCourseById(long id) {
        Optional<Course> optional = courseRepository.findById(id);
        Course course = null;
        if(optional.isPresent()){
            course = optional.get();
        }else {
            throw new RuntimeException("Course not found for ID ::"+ id);
        }
        return course;
    }

//    @Override
//    public Course deleteCourseById(long id) {
//        return courseRepository.deleteById(id);
//    }

    @Override
    public long deleteCourseById(long id) {
        this.courseRepository.deleteById(id);
        return id;
    }
}
