package com.coursemgmt.courses.controller;

import com.coursemgmt.courses.model.Course;
import com.coursemgmt.courses.repository.CourseRepository;
import com.coursemgmt.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    //============================ VIEW AVAILABLE COURSES ============================//

    // display list of courses
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

    // REST API to view JSON of home page
    @GetMapping("/api")
    @ResponseBody
    public List<Course> viewHomePageRestAPI() {
        return courseService.getAllCourses();
    }

    //============================ DISPLAY PAGE TO ADD NEW COURSE ============================//

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        // create model attribute to bind form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";
    }

    //============================ SAVE/UPDATE COURSE DETAILS ============================//

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        // save course to database
        courseService.saveCourse(course);
        return "redirect:/";
    }

    // REST API to save a course
    // TODO : error "org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'text/plain;charset=UTF-8' not supported"
    @PostMapping("/saveCourse/api")
    @ResponseBody
    public Course saveCourseRestAPI(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }

    //============================ VIEW UPDATE FORM ============================//

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get course from the service
        Course course = courseService.getCourseById(id);

        // set course as a model attribute to pre-populate the form
        model.addAttribute("course", course);
        return "update_course";
    }

    // REST API to view update course form
    @GetMapping("/showFormForUpdate/{id}/api")
    @ResponseBody
    public Course showFormForUpdateRestAPI(@PathVariable(value = "id") long id) {
        return courseService.getCourseById(id);
    }

    //============================ DELETE COURSE ============================//

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable(value = "id") long id) {

        // call delete course method
        this.courseService.deleteCourseById(id);
        return "redirect:/";
    }

    // REST API to delete course
    @GetMapping("/deleteCourse/{id}/api")
    @ResponseBody
    public long deleteCourseRestAPI(@PathVariable(value = "id") long id) {
        return courseService.deleteCourseById(id);
    }
}