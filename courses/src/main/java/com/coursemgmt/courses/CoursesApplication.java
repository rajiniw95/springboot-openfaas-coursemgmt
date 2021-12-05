package com.coursemgmt.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;
import com.coursemgmt.courses.monitoring.WorkloadGenerator;
import com.coursemgmt.courses.monitoring.HistogramGenerator;

import java.nio.file.*;
import java.util.List;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) throws Exception {
		// run spring boot app (start up course mgmt system)
		SpringApplication.run(CoursesApplication.class, args);
		
		HTTPRequestGenerator http_req = new HTTPRequestGenerator();
		WorkloadGenerator workload_gen = new WorkloadGenerator();
		HistogramGenerator hist_gen = new HistogramGenerator();
		
		// UPDATE if required (path to user input txt file)
		String user_input_file_location = "/home/rajini/Desktop/coursemgmt-openfaas/user_input.txt";
		
		System.out.println("READING USER INPUT FILE ...");
		System.out.println("===========================");
		
		// extract workload type from user input file 
		String workload_type = workload_gen.get_workload_type(user_input_file_location);
    		System.out.println("The user defined workload type is : " + workload_type);
    		
    		// extract dataset location from user input file 
    		String dataset_location = workload_gen.get_dataset_location(user_input_file_location);
    		System.out.println("The user defined dataset location is : " + dataset_location);
    		
    		// extract retrieve count from user input file 
    		String retrieve_count = workload_gen.get_retrieve_count(user_input_file_location);
    		System.out.println("The user defined retrieve count : " + retrieve_count);
    		
    		// get dataset to memory
    		List<List<String>> dataset = workload_gen.read_dataset(dataset_location);
		System.out.println("INPUT DATASET");   
		System.out.println("===========================");		
		System.out.println(dataset);
		
		// run user-defined workload with dataset
		System.out.println("RUNNING WORKLOAD " + workload_type + " WITH DATASET " + dataset_location);
		System.out.println("===========================");

		try {
			workload_gen.run_workload(user_input_file_location, workload_type, dataset, http_req);
			
            		// http_req.sendGET_home();
            		// http_req.sendGET_new_course();
            		// http_req.sendGET_delete_course("916");
            		// http_req.sendGET_update_form("909");
            		// http_req.sendPOST_save_course("CSMC 3333", "Network", "Ben", "200");
            		// http_req.sendPOST_update_course("922", "CSMC 3335", "Networks", "BenZ", "250");           		
        	} finally {
            		http_req.close();
        	}
        	
        	hist_gen.create_histogram();
    	}
}
