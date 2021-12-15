package com.coursemgmt.courses.controller;

import com.coursemgmt.courses.model.Course;
import com.coursemgmt.courses.service.CourseService;
import com.coursemgmt.courses.CoursesApplication;

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

import org.json.simple.*;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

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
        
            // MONITORING SL invocation : get start time for serverless invocation
            Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
            long start_time = timestamp_start.getTime();

            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // MONITORING SL invocation : get end time for serverless invocation
            Timestamp timestamp_end = new Timestamp(System.currentTimeMillis()); 
            long end_time = timestamp_end.getTime();
            
            // MONITORING SL invocation : calculate time to completion of serverless invocation and add to global ArrayList
            long latency = end_time - start_time;
            CoursesApplication.latency_retrieve_SL.add(latency);

            // extract body of HTTP response
            String response_body = response.body();
            System.out.println("BEGIN RESPONSE BODY FOR GET ALL COURSES");
            response_body = response_body.replaceAll(", $", "");
            System.out.println(response_body);
            System.out.println("END RESPONSE BODY FOR GET ALL COURSES");

            // split response body by commas and assign to array
            String[] array_response = response_body.split(",");

            // get array size
            int array_size = array_response.length;

            // remove extra spaces from all array elements
            String[] trimmed_array = new String[array_size];
            for (int i = 0; i < array_response.length; i++)
                trimmed_array[i] = array_response[i].trim();

            // define number of attributes in class
            int class_size = 5;

            // define list of course objects, to add the gson objects to and return to model
            List<Course> course_obj_list = new ArrayList<Course>();

            // handle when response body is empty
            if (array_size == 1) {
                System.out.println("EMPTY DATABASE FOR GET ALL COURSES");
                // remove index 1 (null) from the response array
                array_response = ArrayUtils.remove(array_response, 0);
                // assign new array size
                array_size = array_response.length;
            }

            // n is defined to iterate over objects
            int n = 1;

            // loop to break response array in to class_size chunks, so that each can be converted to an object
            for (int i = 0; i < array_size; i = i + class_size) {
                // add object elements to arraylist
                List<String> list = new ArrayList<String>();
                int k = i;
                for (int j = 0; j < class_size; j++) {
                    list.add(trimmed_array[k]);
                    k++;
                }

                System.out.println(Arrays.toString(list.toArray()));
                n++;

                // define array list as json string
                String json_str = "{\"id\":\"" + list.get(0) + "\", \"courseCode\":\"" + list.get(1) + "\", \"courseName\":\"" + list.get(2) + "\", \"lecturer\":\"" + list.get(3) + "\", \"credits\":\"" + list.get(4) + "\"}";
                System.out.println(json_str);

                // Creating a Gson Object
                Gson gson = new Gson();

                // convert json string to Course object
                Course course_gson = gson.fromJson(json_str, Course.class);

                // add new course object to course object list
                course_obj_list.add(course_gson);
            }

            // add course object list to model
            model.addAttribute("listCourses", course_obj_list);
            return "index";
        } catch (Exception e) {
            return e.toString();
        }
    }

    //============================ DISPLAY PAGE TO ADD NEW COURSE ============================//

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        // create model attribute to bind form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";
    }
    
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
            // MONITORING SL invocation : get start time for serverless invocation
            Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
            long start_time = timestamp_start.getTime();
            
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // MONITORING SL invocation : get end time for serverless invocation
            Timestamp timestamp_end = new Timestamp(System.currentTimeMillis()); 
            long end_time = timestamp_end.getTime();
            
            // MONITORING SL invocation : calculate time to completion of serverless invocation and add to global ArrayList
            long latency = end_time - start_time;
            CoursesApplication.latency_retrieve_by_id_SL.add(latency);

            // extract body of HTTP response
            String response_body = response.body();
            System.out.println("BEGIN RESPONSE BODY FOR GET COURSE BY ID");
            response_body = response_body.replaceAll(", $", "");
            System.out.println(response_body);
            System.out.println("END RESPONSE BODY FOR GET COURSE BY ID");

            // put response body to array and assign elements to variables
            String[] course_detail_list = response_body.split(",");
            String response_course_id = course_detail_list[0];
            long long_course_id = Long.parseLong(response_course_id);
            String response_course_code = course_detail_list[1];
            String response_course_name = course_detail_list[2];
            String response_lecturer = course_detail_list[3];
            String response_credits = course_detail_list[4];
            String trimmed_response_creedits = response_credits.trim();
            int int_credits = Integer.parseInt(trimmed_response_creedits);

            // create course object with response variables 
            Course course = new Course();
            course.setId(long_course_id);
            course.setCourseCode(response_course_code);
            course.setCourseName(response_course_name);
            course.setLecturer(response_lecturer);
            course.setCredits(int_credits);

            // set course as a model attribute to pre-populate the form
            model.addAttribute("course", course);
            return "update_course";

        } catch (Exception e) {
            return e.toString();
        }
    }

    //============================ SAVE NEW COURSE DETAILS ============================//

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {

        // assign object attributes to variables 
        String input_course_code = course.getCourseCode();
        String input_course_name = course.getCourseName();
        String input_lecturer = course.getLecturer();
        int input_credits = course.getCredits();
        String string_input_credits = String.valueOf(input_credits);

        // construct parameter for http request with object attributes
        String uri_parameter = input_course_code + "," + input_course_name + "," + input_lecturer + "," + string_input_credits;
        // replace all spaces in uri with %20
        uri_parameter = uri_parameter.replaceAll(" ", "%20");

        System.out.println("UPDATING DATABASE WITH NEW COURSE...");
        System.out.println(uri_parameter);

        // set uri to savecourse serverless function with paramemeter uri_parameter
        String uri = "http://127.0.0.1:31112/function/savecourse?data=" + uri_parameter;

        try {
            // MONITORING SL invocation : get start time for serverless invocation
            Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
            long start_time = timestamp_start.getTime();
            
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // MONITORING SL invocation : get end time for serverless invocation
            Timestamp timestamp_end = new Timestamp(System.currentTimeMillis()); 
            long end_time = timestamp_end.getTime();
            
            // MONITORING SL invocation : calculate time to completion of serverless invocation and add to global ArrayList
            long latency = end_time - start_time;
            CoursesApplication.latency_save_SL.add(latency);
            
            // MONITORING DB operations : extract latency from response and add to global ArrayList
            String response_body = response.body();
            String[] response_list = response_body.split(",");
            String db_latency_str = response_list[0];
            long db_latency = Long.parseLong(db_latency_str);
            CoursesApplication.latency_save_DB.add(db_latency);

            // load course saved successfully page 
            return "new_course_saved";
        } catch (Exception e) {
            return e.toString();
        }

    }

    //============================ UPDATE COURSE DETAILS ============================//

    @PostMapping("/updateCourse")
    public String updateCourse(@ModelAttribute("course") Course course) {

        // assign object attributes to variables 
        long input_id = course.getId();
        String string_input_id = String.valueOf(input_id);
        String input_course_code = course.getCourseCode();
        String input_course_name = course.getCourseName();
        String input_lecturer = course.getLecturer();
        int input_credits = course.getCredits();
        String string_input_credits = String.valueOf(input_credits);

        // construct parameter for http request with object attributes
        String uri_parameter = string_input_id + "," + input_course_code + "," + input_course_name + "," + input_lecturer + "," + string_input_credits;
        // replace all spaces in uri with %20
        uri_parameter = uri_parameter.replaceAll(" ", "%20");


        System.out.println("INPUT COURSE ID == ");
        System.out.println(string_input_id);

        System.out.println("UPDATING DATABASE WITH COURSE...");
        System.out.println(uri_parameter);

        // set uri to updatecourse serverless function with paramemeter uri_parameter
        String uri = "http://127.0.0.1:31112/function/updatecourse?data=" + uri_parameter;

        try {
            // MONITORING SL invocation : get start time for serverless invocation
            Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
            long start_time = timestamp_start.getTime();
            
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // MONITORING SL invocation : get end time for serverless invocation
            Timestamp timestamp_end = new Timestamp(System.currentTimeMillis()); 
            long end_time = timestamp_end.getTime();
            
            // MONITORING SL invocation : calculate time to completion of serverless invocation and add to global ArrayList
            long latency = end_time - start_time;
            CoursesApplication.latency_update_SL.add(latency);
            
            // MONITORING DB operations : extract latency from response and add to global ArrayList
            String response_body = response.body();
            String[] response_list = response_body.split(",");
            String db_latency_str = response_list[0];
            long db_latency = Long.parseLong(db_latency_str);
            CoursesApplication.latency_update_DB.add(db_latency);

            // load course saved successfully page 
            return "course_updated";
        } catch (Exception e) {
            return e.toString();
        }

    }
    
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
            // MONITORING SL invocation : get start time for serverless invocation
            Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
            long start_time = timestamp_start.getTime();
            
            // send HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            // get HTTP response
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // MONITORING SL invocation : get end time for serverless invocation
            Timestamp timestamp_end = new Timestamp(System.currentTimeMillis()); 
            long end_time = timestamp_end.getTime();
            
            // MONITORING SL invocation : calculate time to completion of serverless invocation and add to global ArrayList
            long latency = end_time - start_time;
            CoursesApplication.latency_delete_SL.add(latency);
            
            // MONITORING DB operations : extract latency from response and add to global ArrayList
            String response_body = response.body();
            String[] response_list = response_body.split(",");
            String db_latency_str = response_list[0];
            long db_latency = Long.parseLong(db_latency_str);
            CoursesApplication.latency_delete_DB.add(db_latency);

            // load course deleted successfully page 
            return "course_deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
