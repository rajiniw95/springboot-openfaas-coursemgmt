package com.coursemgmt.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(CoursesApplication.class, args);
		
		HTTPRequestGenerator httpreq = new HTTPRequestGenerator();
		
		try {
            		httpreq.sendGET_home();
            		httpreq.sendGET_update_form("909");
            		// httpreq.sendGET_new_course();
            		// httpreq.sendPOST_save_course();
            		// httpreq.sendGET_delete_course("916");
        	} finally {
            		httpreq.close();
        	}
    	}
}
