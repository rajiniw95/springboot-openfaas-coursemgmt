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
    
        String uri = "http://127.0.0.1:31112/function/getallcourses";

        try {
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

	    // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // extract body of HTTP response
            String response_body = response.body();
            System.out.println("BEGIN RESPONSE BODY FOR GET ALL COURSES");
            response_body = response_body.replaceAll(", $", "");
            System.out.println(response_body);
            System.out.println("END RESPONSE BODY FOR GET ALL COURSES");
            
            String[] array_response = response_body.split(",");

            return "index";
        } catch (Exception e) {
            return e.toString();
        }
        
    
        //model.addAttribute("listCourses", courseService.getAllCourses());
	//return "index";
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
        
        // assign object attributes to variables 
        String input_course_code = course.getCourseCode();
        String input_course_name = course.getCourseName();
        String input_lecturer = course.getLecturer();
        int input_credits = course.getCredits();
        String string_input_credits =String.valueOf(input_credits);
        
        // construct parameter for http request with object attributes
        String uri_parameter = input_course_code + ","+ input_course_name + ","+ input_lecturer + ","+ string_input_credits;
        // replace all spaces in uri with %20
        uri_parameter = uri_parameter.replaceAll(" ", "%20");
        
        System.out.println("UPDATING DATABASE WITH COURSE...");
	System.out.println(uri_parameter);
	
	// set uri to savecourse serverless function with paramemeter uri_parameter
	String uri = "http://127.0.0.1:31112/function/savecourse?data=" + uri_parameter;
	
	try {
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

	    // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                 
            // save course to database
            // courseService.saveCourse(course);
            
            // redirect to home page 
            return "redirect:/";
        } catch (Exception e) {
            return e.toString();
        }

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

	// check course ID
	long cid = id;
	System.out.println("PRINT ID FOR GET COURSE BY ID...");
	System.out.println(cid);
	System.out.println("END ID FOR GET COURSE BY ID");
	
	// set uri to getcoursebyid serverless function with paramemeter cid
	String uri = "http://127.0.0.1:31112/function/getcoursebyid?cid=" + cid;

        try {
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

	    // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // extract body of HTTP response
            String response_body = response.body();
            System.out.println("BEGIN RESPONSE BODY FOR GET COURSE BY ID");
            response_body = response_body.replaceAll(", $", "");
            System.out.println(response_body);
            System.out.println("END RESPONSE BODY FOR GET COURSE BY ID");
            
            // put response body to array and assign elements to variables
            String[] course_detail_list = response_body.split(",");
            String response_course_code = course_detail_list[0];
            String response_course_name = course_detail_list[1];
            String response_lecturer = course_detail_list[2];
            String response_credits = course_detail_list[3];
            String trimmed_response_creedits = response_credits.trim();
            int int_credits=Integer.parseInt(trimmed_response_creedits);  
                   
            // create course object with response variables 
            Course course = new Course();
            course.setCourseCode(response_course_code);
            course.setCourseName(response_course_name);
            course.setLecturer(response_lecturer);
            course.setCredits(int_credits);
            
            //Course course = courseService.getCourseById(id);

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
    
        // check course ID
    	System.out.println("PRINT ID FOR DELETE COURSE BY ID...");
	System.out.println(id);
	System.out.println("END ID FOR DELETE COURSE BY ID");
	
	// set uri to deletecoursebyid serverless function with paramemeter cid
	String uri = "http://127.0.0.1:31112/function/deletecoursebyid?cid=" + id;

        try {
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

	    // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            // call delete course method
            //this.courseService.deleteCourseById(id);
            
            // redirect to home page 
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
