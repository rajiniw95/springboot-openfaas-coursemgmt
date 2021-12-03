package com.coursemgmt.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;
import com.coursemgmt.courses.monitoring.WorkloadGenerator;

import java.nio.file.*;;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) throws Exception {
		// run spring boot app (start up course mgmt system)
		SpringApplication.run(CoursesApplication.class, args);
		
		HTTPRequestGenerator http_req = new HTTPRequestGenerator();
		WorkloadGenerator workload_gen = new WorkloadGenerator();
		
		// define location of user input file 
		String workload_type = workload_gen.get_workload_type("/home/rajini/Desktop/coursemgmt-openfaas/user_input.txt");
    		System.out.println(workload_type);
    		workload_gen.run_workload(workload_type);
				
		try {
            		// http_req.sendGET_home();
            		// http_req.sendGET_new_course();
            		// http_req.sendGET_delete_course("916");
            		// http_req.sendGET_update_form("909");
            		// http_req.sendPOST_save_course("CSMC 3333", "Network", "Ben", "200");
            		// http_req.sendPOST_update_course("922", "CSMC 3335", "Networks", "BenZ", "250");           		
        	} finally {
            		http_req.close();
        	}
    	}
}
