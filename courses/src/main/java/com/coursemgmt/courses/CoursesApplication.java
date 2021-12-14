package com.coursemgmt.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;
import com.coursemgmt.courses.monitoring.WorkloadGenerator;
import com.coursemgmt.courses.monitoring.HistogramGenerator;

import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class CoursesApplication {

	// define lists to record latency values of serverless invocations
	public static ArrayList<Long> latency_retrieve_SL = new ArrayList<Long>();
	public static ArrayList<Long> latency_retrieve_by_id_SL = new ArrayList<Long>();
	public static ArrayList<Long> latency_save_SL = new ArrayList<Long>();
	public static ArrayList<Long> latency_update_SL = new ArrayList<Long>();
	public static ArrayList<Long> latency_delete_SL = new ArrayList<Long>();

	// define lists to record latency values of database operations of serverless ivocations
	public static ArrayList<Long> latency_retrieve_DB = new ArrayList<Long>();
	public static ArrayList<Long> latency_retrieve_by_id_DB = new ArrayList<Long>();
	public static ArrayList<Long> latency_save_DB = new ArrayList<Long>();
	public static ArrayList<Long> latency_update_DB = new ArrayList<Long>();
	public static ArrayList<Long> latency_delete_DB = new ArrayList<Long>();

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

		// array for delta_duration output of run_workload
		ArrayList<Long> delta_durations = new ArrayList<Long>();
	
		// run the user defined workload
		try {
			delta_durations = workload_gen.run_workload(user_input_file_location, workload_type, dataset, http_req);          		
        	} finally {
            		http_req.close();
        	}
        	
        	// print output latency array
        	System.out.println("Latency durations for the entire workload : " + delta_durations.toString());
        	
        	// get the different types of HTTP requests in the user defined workload
        	List<String> request_types = workload_gen.get_workload_request_types(workload_type);
        	System.out.println("The types of HTTP requests in the workload : " + Arrays.toString(request_types.toArray()));
        	
        	// define ArrayLists for workloads with several HTTP request types
        	ArrayList<Long> latency_request_type_1 = new ArrayList<Long>();
        	ArrayList<Long> latency_request_type_2 = new ArrayList<Long>();
        	ArrayList<Long> latency_request_type_3 = new ArrayList<Long>();
        	ArrayList<Long> latency_request_type_4 = new ArrayList<Long>();
        	
        	// Create Histogram for ONE HTTP REQUEST TYPE
        	if ((workload_type.equals("workload_B")) || (workload_type.equals("workload_C")) || (workload_type.equals("workload_E"))) {
  			// create histogram for each latency list
  			hist_gen.create_histogram(workload_type, request_types.get(0), output_histogram_location, delta_durations);  			
		}
        	        	
        	// Create Histogram for TWO HTTP REQUEST TYPES
        	// break up latency array in workloads where two HTTP request types are inter-mixed 
        	// i.e. two HTTP request types are called consecutively on each record
        	if ((workload_type.equals("workload_A")) || (workload_type.equals("workload_G"))) {
        		for (int i = 0; i < delta_durations.size(); i += 2) {
            			latency_request_type_1.add(delta_durations.get(i));
            			latency_request_type_2.add(delta_durations.get(i+1));      
    			}
  			System.out.println("Latency durations for request type 01 : " + latency_request_type_1.toString());
  			System.out.println("Latency durations for request type 02 : " + latency_request_type_2.toString());
  			
  			// create histogram for each latency list
  			System.out.println("Creating histogram for request type 01 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(0), output_histogram_location, latency_request_type_1);
  			System.out.println("Creating histogram for request type 02 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(1), output_histogram_location, latency_request_type_2); 			
		}
		
		// Create Histogram for TWO HTTP REQUEST TYPES
        	// The workload considered has two cases where the database size grows dynamically
		if (workload_type.equals("workload_D")) {
			int int_retrieve_count = Integer.parseInt(retrieve_count);  
			// CASE 01: latency array size is 2*retrieve_count
			if (dataset.size() >= int_retrieve_count) {
				for (int i = 0; i < delta_durations.size(); i += 2) {
            				latency_request_type_1.add(delta_durations.get(i));
            				latency_request_type_2.add(delta_durations.get(i+1));      
    				}
    			} 
    			// CASE 02: extra retrieve requests
    			else {
    				// corresponding latencies for dataset
    				for (int i = 0; i < dataset.size()*2; i += 2) {
            				latency_request_type_1.add(delta_durations.get(i)); 
            				latency_request_type_2.add(delta_durations.get(i+1));    
    				}
    				// corresponding latencies for excess retrieve requests
    				for (int j = dataset.size()*2; j < delta_durations.size(); j++) {
            				latency_request_type_2.add(delta_durations.get(j));     
    				}
    			}
    			
  			System.out.println("Latency durations for request type 01 : " + latency_request_type_1.toString());
  			System.out.println("Latency durations for request type 02 : " + latency_request_type_2.toString());
  			
  			// create histogram for each latency list
  			System.out.println("Creating histogram for request type 01 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(0), output_histogram_location, latency_request_type_1);
  			System.out.println("Creating histogram for request type 02 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(1), output_histogram_location, latency_request_type_2); 			
		}
		
		// Create Histogram for TWO HTTP REQUEST TYPES
        	// break up latency array in workloads where two HTTP request types are called  
        	// i.e. one HTTP request type is called on the entire dataset and then another request type is called on the entire dataset
        	// IMPORTANT : delete all existing records in database with workload_E before executing workload_F 
        	// (otherwise array splitting logic is not consistent with the 50/50 considered here) 
        	if (workload_type.equals("workload_F")) {
        		for (int i = 0; i < dataset.size(); i++) {
            			latency_request_type_1.add(delta_durations.get(i));     
    			}
    			for (int j = dataset.size(); j < delta_durations.size(); j++) {
            			latency_request_type_2.add(delta_durations.get(j));     
    			}
  			System.out.println("Latency durations for request type 01 : " + latency_request_type_1.toString());
  			System.out.println("Latency durations for request type 02 : " + latency_request_type_2.toString());
  			
  			// create histogram for each latency list
  			System.out.println("Creating histogram for request type 01 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(0), output_histogram_location, latency_request_type_1);
  			System.out.println("Creating histogram for request type 02 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(1), output_histogram_location, latency_request_type_2); 			
		}
		
		// Create Histogram for FOUR HTTP REQUEST TYPES
        	// break up latency array in workloads where four HTTP request types are inter-mixed (WORKLOAD H)
        	// i.e. one HTTP request type is called for all records in dataset, then three request types are called consecutively on each record
        	// IMPORTANT : delete all existing records in database with workload_E before executing workload_F 
        	// (otherwise array splitting logic is not consistent with the 25/25/25/25 considered here) 
        	if (workload_type.equals("workload_H")) {
        		int save_count = delta_durations.size()/4;
        		System.out.println("save count : " + save_count);
        		for (int i = 0; i < save_count; i++) {
            			latency_request_type_1.add(delta_durations.get(i));     
    			}
    			for (int j = save_count; j < delta_durations.size(); j += 3) {
            			latency_request_type_2.add(delta_durations.get(j));
            			latency_request_type_3.add(delta_durations.get(j+1));
            			latency_request_type_4.add(delta_durations.get(j+2));      
    			}
    			
  			System.out.println("Latency durations for HTTP request type 01 : " + latency_request_type_1.toString());
  			System.out.println("Latency durations for HTTP request type 02 : " + latency_request_type_2.toString());
  			System.out.println("Latency durations for HTTP request type 03 : " + latency_request_type_3.toString());
  			System.out.println("Latency durations for HTTP request type 04 : " + latency_request_type_4.toString());
  			
  			// create histogram for each latency list
  			System.out.println("Creating histogram for HTTP request type 01 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(0), output_histogram_location, latency_request_type_1);
  			System.out.println("Creating histogram for HTTP request type 02 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(1), output_histogram_location, latency_request_type_2); 
  			System.out.println("Creating histogram for HTTP request type 03 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(2), output_histogram_location, latency_request_type_3); 
  			System.out.println("Creating histogram for HTTP request type 04 ... ");
  			hist_gen.create_histogram(workload_type, request_types.get(3), output_histogram_location, latency_request_type_4); 			
		}
		
		System.out.println("============================================");
		System.out.println("LATENCY ANALYSIS COMPLETED FOR HTTP REQUESTS");
		System.out.println("============================================");
		
		System.out.println("Latency values for getAllCourses serverless invocations : " + latency_retrieve_SL.toString());
		System.out.println("Latency values for getCourseById serverless invocations : " + latency_retrieve_by_id_SL.toString());
		System.out.println("Latency values for saveCourse serverless invocations : " + latency_save_SL.toString());
		System.out.println("Latency values for updateCourse serverless invocations : " + latency_update_SL.toString());
		System.out.println("Latency values for deleteCourseById serverless invocations : " + latency_delete_SL.toString());
		
        	if (latency_retrieve_SL.isEmpty() == false) {
        		System.out.println("Creating histogram for getAllCourses serverless invocations ... ");
  			hist_gen.create_histogram(workload_type, "SL_getAllCourses" , output_histogram_location, latency_retrieve_SL); }
            		
            	if (latency_retrieve_by_id_SL.isEmpty() == false) {
        		System.out.println("Creating histogram for getCourseById serverless invocations ... ");
  			hist_gen.create_histogram(workload_type, "SL_getCourseById" , output_histogram_location, latency_retrieve_by_id_SL); }
            		
            	if (latency_save_SL.isEmpty() == false) { 
        		System.out.println("Creating histogram for saveCourse serverless invocations ... ");
  			hist_gen.create_histogram(workload_type, "SL_saveCourse" , output_histogram_location, latency_save_SL); } 
            		
            	if (latency_update_SL.isEmpty() == false) { 
        		System.out.println("Creating histogram for updateCourse serverless invocations ... ");
  			hist_gen.create_histogram(workload_type, "SL_updateCourse" , output_histogram_location, latency_update_SL); } 
            		
            	if (latency_delete_SL.isEmpty() == false) {
        		System.out.println("Creating histogram for deleteCourseById serverless invocations ... ");
  			hist_gen.create_histogram(workload_type, "SL_deleteCourseById" , output_histogram_location, latency_delete_SL); }
		
		System.out.println("=====================================================");
		System.out.println("LATENCY ANALYSIS COMPLETED FOR SERVERLESS INVOCATIONS");
		System.out.println("=====================================================");
		
		System.out.println("Latency values for database operations of getAllCourses serverless invocations : " + latency_retrieve_DB.toString());
		System.out.println("Latency values for database operations of getCourseById serverless invocations : " + latency_retrieve_by_id_DB.toString());
		System.out.println("Latency values for database operations of saveCourse serverless invocations : " + latency_save_DB.toString());
		System.out.println("Latency values for database operations of updateCourse serverless invocations : " + latency_update_DB.toString());
		System.out.println("Latency values for database operations of deleteCourseById serverless invocations : " + latency_delete_DB.toString());
		
		System.out.println("==================================================");
		System.out.println("LATENCY ANALYSIS COMPLETED FOR DATABASE OPERATIONS");
		System.out.println("==================================================");
    	}
}
