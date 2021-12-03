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
            		// httpreq.sendGET_home();
            		// httpreq.sendGET_new_course();
            		// httpreq.sendGET_delete_course("916");
            		// httpreq.sendGET_update_form("909");
            		// httpreq.sendPOST_save_course("CSMC 3333", "Network", "Ben", "200");
            		// httpreq.sendPOST_update_course("922", "CSMC 3335", "Networks", "BenZ", "250");           		
        	} finally {
            		httpreq.close();
        	}
    	}
}
