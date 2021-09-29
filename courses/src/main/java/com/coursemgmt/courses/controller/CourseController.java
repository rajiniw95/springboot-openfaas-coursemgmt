package com.coursemgmt.courses.controller;

import com.coursemgmt.courses.model.Course;
import com.coursemgmt.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    HttpClient client = HttpClient.newHttpClient();

    //============================ VIEW AVAILABLE COURSES ============================//

    // display list of courses
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

//    // REST API to view JSON of home page
//    @GetMapping("/api")
//    @ResponseBody
//    public List<Course> viewHomePageRestAPI() {
//        return courseService.getAllCourses();
//    }

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

//    // REST API to save a course
//    @PostMapping(value = "/saveCourse/api", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public Course saveCourseRestAPI(@Valid @RequestBody Course course) {
//        return courseService.saveCourse(course);
//    }

    //============================ VIEW UPDATE FORM ============================//

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

	long cid = id;
	System.out.println("PRINT ID FOR GET COURSE BY ID...");
	System.out.println(cid);
	System.out.println("END ID FOR GET COURSE BY ID");
	
	String uri = "http://127.0.0.1:31112/function/getcoursebyid?cid=" + cid;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            //ArrayList<String> course_detail_list = new ArrayList<>(Arrays.asList(response.split(",")));
            String response_body = response.body();
            System.out.println("BEGIN RESPONSE BODY FOR GET COURSE BY ID");
            System.out.println(response_body);
            System.out.println("BEGIN RESPONSE BODY FOR GET COURSE BY ID");
            String[] course_detail_list = response_body.split(",");
            
            String response_course_code = course_detail_list[0];
            String response_course_name = course_detail_list[1];
            String response_lecturer = course_detail_list[2];
            String response_credits = course_detail_list[3];
            int int_credits = Integer.valueOf(response_credits); 
            
	    ///////  TO DO : create Course object with above parameters            

            // get course from the service
            Course course = courseService.getCourseById(id);
            //Course course = new Course(cid, response_course_code, response_course_name, response_lecturer, response_credits);

            // set course as a model attribute to pre-populate the form
            model.addAttribute("course", course);
            return "update_course";

        } catch (Exception e) {
            return e.toString();
        }
    }

//    // REST API to view update course form
//    @GetMapping("/showFormForUpdate/{id}/api")
//    @ResponseBody
//    public Course showFormForUpdateRestAPI(@PathVariable(value = "id") long id) {
//        return courseService.getCourseById(id);
//    }

    //============================ DELETE COURSE ============================//

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable(value = "id") long id) {
    
    	System.out.println("PRINT ID FOR DELETE COURSE BY ID...");
	System.out.println(id);
	System.out.println("END ID FOR DELETE COURSE BY ID");
	
	String uri = "http://127.0.0.1:31112/function/deletecoursebyid?cid=" + id;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            // call delete course method
            //this.courseService.deleteCourseById(id);
            return "redirect:/";
        } catch (Exception e) {
            return e.toString();
        }
    }

//    // REST API to delete course
//    @GetMapping("/deleteCourse/{id}/api")
//    @ResponseBody
//    public long deleteCourseRestAPI(@PathVariable(value = "id") long id) {
//        return courseService.deleteCourseById(id);
//    }
}
