package com.coursemgmt.courses.controller;

import com.coursemgmt.courses.model.Course;
import com.coursemgmt.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    // display list of courses
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

    // Rest controller for viewHomePage method -- duplicate method with @ResponseBody annotation
    @GetMapping("/api")
    @ResponseBody
    public String viewHomePageRestAPI(Model model){
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model){
        // create model attribute to bind form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course){
        // save course to database
        courseService.saveCourse(course);
        return "redirect:/";
    }

    // Rest controller for showNewCourseForm method -- duplicate method with @ResponseBody annotation
    @PostMapping("/saveCourse/api")
    @ResponseBody
    public String saveCourseRestAPI(@ModelAttribute("course") Course course){
        // save course to database
        courseService.saveCourse(course);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get course from the service
        Course course = courseService.getCourseById(id);

        // set course as a model attribute to pre-populate the form
        model.addAttribute(  "course", course);
        return "update_course";
    }

    // Rest controller for showFormForUpdate method -- duplicate method with @ResponseBody annotation
    @GetMapping("/showFormForUpdate/{id}/api")
    @ResponseBody
    public String showFormForUpdateRestAPI(@PathVariable( value = "id") long id, Model model) {

        // get course from the service
        Course course = courseService.getCourseById(id);

        // set course as a model attribute to pre-populate the form
        model.addAttribute(  "course", course);
        return "update_course";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id) {

        // call delete course method
        this.courseService.deleteCourseById(id);
        return "redirect:/";
    }

    // Rest controller for deleteCourse method -- duplicate method with @ResponseBody annotation
    @GetMapping("/deleteCourse/{id}/api")
    @ResponseBody
    public String deleteCourseRestAPI(@PathVariable (value = "id") long id) {

        // call delete course method
        this.courseService.deleteCourseById(id);
        return "redirect:/";
    }
}
