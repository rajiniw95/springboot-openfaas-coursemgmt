package com.coursemgmt.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;
import com.coursemgmt.courses.monitoring.WorkloadGenerator;
import com.coursemgmt.courses.monitoring.HistogramGenerator;

import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;

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
    		
    		// extract output histogram location from user input file 
    		String output_histogram_location = workload_gen.get_output_histogram_location(user_input_file_location);
    		System.out.println("The user defined output histogram location : " + output_histogram_location);
    		
    		// get dataset to memory
    		List<List<String>> dataset = workload_gen.read_dataset(dataset_location);
		System.out.println("INPUT DATASET");   
		System.out.println("===========================");		
		System.out.println(dataset);
		
		// run user-defined workload with dataset
		System.out.println("RUNNING WORKLOAD " + workload_type + " WITH DATASET " + dataset_location);
		System.out.println("===========================");

		ArrayList<Long> delta_durations = new ArrayList<Long>();
	
		try {
			delta_durations = workload_gen.run_workload(user_input_file_location, workload_type, dataset, http_req);          		
        	} finally {
            		http_req.close();
        	}
        	
        	System.out.println(delta_durations.toString());
						
        	hist_gen.create_histogram(workload_type, output_histogram_location, delta_durations);
    	}
}
