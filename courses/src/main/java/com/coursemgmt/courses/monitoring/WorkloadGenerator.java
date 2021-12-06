package com.coursemgmt.courses.monitoring;

import java.nio.file.*;
import java.io.*;

import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import java.io.FileReader;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import com.opencsv.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import java.sql.Timestamp;  

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;

// DEFINED HTTP REQUEST METHODS
// http_req.sendGET_home();
// http_req.sendGET_new_course();
// http_req.sendGET_delete_course("916");
// http_req.sendGET_update_form("909");
// http_req.sendPOST_save_course("CSMC 3333", "Network", "Ben", "200");
// http_req.sendPOST_update_course("922", "CSMC 3335", "Networks", "BenZ", "250"); 

public class WorkloadGenerator {
		
	// method to read user input file as string
  	public String read_file_as_string(String file_name)throws Exception
  	{
    		String data = "";
    		data = new String(Files.readAllBytes(Paths.get(file_name)));
    		return data;
 	}
		
	// method to extract workload_type from user input file
	public String get_workload_type(String file_name)throws Exception 
	{
		// read file as string
   		String data = read_file_as_string(file_name);
    					
    		// split rows to the segments array
    		String segments[] = data.split(";");
    		
    		// string matching to find array element starting with "workload_type"
    		Pattern pattern = Pattern.compile("workload_type");  
      		String[] user_input;
      		String defined_workload_type = "";
    		for (String s: segments) {  
    			user_input = s.split(" = ", 2); 
    			Matcher matcher = pattern.matcher(user_input[0]);        
    			if (matcher.find()) {    				
  				defined_workload_type = user_input[1]; 
			}
		}
		
    		return defined_workload_type;
  	}
  	
  	// method to extract workload_type from user input file
	public String get_dataset_location(String file_name)throws Exception 
	{
		// read file as string
   		String data = read_file_as_string(file_name);
    					
    		// split rows to the segments array
    		String segments[] = data.split(";");
    		
    		// string matching to find array element starting with "workload_type"
    		Pattern pattern = Pattern.compile("dataset_location");  
      		String[] user_input;
      		String defined_dataset_location = "";
    		for (String s: segments) {  
    			user_input = s.split(" = ", 2); 
    			Matcher matcher = pattern.matcher(user_input[0]);        
    			if (matcher.find()) {    				
  				defined_dataset_location = user_input[1]; 
			}
		}
		
    		return defined_dataset_location;
  	}
  	
  	// method to extract retrieve_count from user input file
	public String get_retrieve_count(String file_name)throws Exception 
	{
		// read file as string
   		String data = read_file_as_string(file_name);
    					
    		// split rows to the segments array
    		String segments[] = data.split(";");
    		
    		// string matching to find array element starting with "workload_type"
    		Pattern pattern = Pattern.compile("retrieve_count");  
      		String[] user_input;
      		String defined_retrieve_count = "";
    		for (String s: segments) {  
    			user_input = s.split(" = ", 2); 
    			Matcher matcher = pattern.matcher(user_input[0]);        
    			if (matcher.find()) {    				
  				defined_retrieve_count = user_input[1]; 
			}
		}
		
    		return defined_retrieve_count;
  	}
  	
  	// method to extract output_histogram_location from user input file
	public String get_output_histogram_location(String file_name)throws Exception 
	{
		// read file as string
   		String data = read_file_as_string(file_name);
    					
    		// split rows to the segments array
    		String segments[] = data.split(";");
    		
    		// string matching to find array element starting with "workload_type"
    		Pattern pattern = Pattern.compile("output_histogram_location");  
      		String[] user_input;
      		String defined_output_histogram_location = "";
    		for (String s: segments) {  
    			user_input = s.split(" = ", 2); 
    			Matcher matcher = pattern.matcher(user_input[0]);        
    			if (matcher.find()) {    				
  				defined_output_histogram_location = user_input[1]; 
			}
		}
		
    		return defined_output_histogram_location;
  	}
  	
  	// method to run the user defined workload 
  	public ArrayList<Long> run_workload(String filename, String workload_type, List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
  		workload_type = workload_type.trim();
  		
  		ArrayList<Long> delta_durations = new ArrayList<Long>();
  		
    		if (workload_type.equals("workload_A")) {
    			delta_durations = workload_A(dataset, http_req);
    		}
    		
    		if (workload_type.equals("workload_B")) {
    			delta_durations = workload_B(dataset, http_req);
    		}

    		if (workload_type.equals("workload_C")) {
    			delta_durations = workload_C(filename, http_req);
    		}

    		if (workload_type.equals("workload_D")) {
    			delta_durations = workload_D(filename, dataset, http_req);
    		}
    		
    		if (workload_type.equals("workload_E")) {
    			delta_durations = workload_E(http_req);
    		}  
    		
    		if (workload_type.equals("workload_F")) {
    			delta_durations = workload_F(dataset, http_req);
    		}  
    		
    		if (workload_type.equals("workload_G")) {
    			delta_durations = workload_G(dataset, http_req);
    		} 
    		
    		if (workload_type.equals("workload_H")) {
    			delta_durations = workload_H(dataset, http_req);
    		}   
    		
    		return delta_durations;	
 	}
 	
 	// workload_A (CREATE_HEAVY)
 	// 50% HTTP with no serverless invokations, 50% CREATE
 	// load new course form and then save new course (repeated for all records in dataset)  
 	// follows the natural order of HTTP request (new course form --> enter data to form --> save new course)	
	// ZIPFIAN
  	public ArrayList<Long> workload_A(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
  		ArrayList<Long> delta_durations_A = new ArrayList<Long>();
  		
  		int number_records = dataset.size();
	    	for (int i = 0; i < number_records; i++) {
	    		System.out.println(i);
	    		
	    		// load new course form -- no serverless function or database operation invoked
  			http_req.sendGET_new_course();
  			
  			// get current record (i^th)
  			List<String> record = new ArrayList<String>(4);
  			record = dataset.get(i);
  			System.out.println(record);
  			
  			// separate current record to Course attributes
  			String course_code = record.get(0);
  			String course_name = record.get(1);
  			String lecturer = record.get(2);
  			String credits = record.get(3);
  			
  			// Create new course record in database
  			http_req.sendPOST_save_course(course_code, course_name, lecturer, credits);
		}
		
		return delta_durations_A;
 	}
 	
	// workload_B (CREATE_ONLY)
 	// 100% CREATE
 	// send HTTP request for save new course (repeated for all records in dataset)  
	// ZIPFIAN
  	public ArrayList<Long> workload_B(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{	
  		int number_records = dataset.size();
  		
  		ArrayList<Long> delta_durations_B = new ArrayList<Long>();
  		
	    	for (int i = 0; i < number_records; i++) {
	    		System.out.println(i);
	    	
  			// get current record (i^th)
  			List<String> record = new ArrayList<String>(4);
  			record = dataset.get(i);
  			System.out.println(record);
  			
  			// separate current record to Course attributes
  			String course_code = record.get(0);
  			String course_name = record.get(1);
  			String lecturer = record.get(2);
  			String credits = record.get(3);
  			
  			// MONITORING: get start time for HTTP request
  			Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
  			long start_time = timestamp_start.getTime();
  			
  			// Create new course record in database
  			http_req.sendPOST_save_course(course_code, course_name, lecturer, credits);

			// MONITORING: get end time for HTTP request
			Timestamp timestamp_end = new Timestamp(System.currentTimeMillis());
  			long end_time = timestamp_end.getTime();
  			
  			// MONITORING: calculate time to completion of HTTP request and add to output ArrayList
  			long delta_duration = end_time - start_time;
  			delta_durations_B.add(delta_duration);
		}
		
		return delta_durations_B;
 	}
 	
 	// workload_C (RETRIEVE_ONLY_STATIC)
 	// 100% RETRIEVE when Database size is static
 	// send HTTP request for get home page (repeated for retrieve_count)  	
  	public ArrayList<Long> workload_C(String filename, HTTPRequestGenerator http_req) throws Exception
  	{
  		String retrieve_count = get_retrieve_count(filename);
  		int int_retrieve_count=Integer.parseInt(retrieve_count); 
  		
  		ArrayList<Long> delta_durations_C = new ArrayList<Long>();
  		
  		// call homepage HTTP request for 'retrieve_count' number of times
	    	for (int i = 0; i < int_retrieve_count; i++) {
	    		System.out.println(i);
	    		
	    		// MONITORING: get start time for HTTP request
  			Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
  			long start_time = timestamp_start.getTime();
  			
	    		http_req.sendGET_home();
	    		
	    		// MONITORING: get end time for HTTP request
			Timestamp timestamp_end = new Timestamp(System.currentTimeMillis());
  			long end_time = timestamp_end.getTime();
  			
  			// MONITORING: calculate time to completion of HTTP request and add to output ArrayList
  			long delta_duration = end_time - start_time;
  			delta_durations_C.add(delta_duration);
		}
		
		return delta_durations_C;
 	}
 	
 	// workload_D (RETRIEVE_DYNAMIC)
 	// 50% CREATE 50% RETRIEVE as the Database size grows
 	// if number_records >= int_retrieve_count --> then call save+get for retrieve_count # of times
 	// if number_records < int_retrieve_count --> then call save+get for number_records # of times AND get for delta number of times
 	// We increase the size of the database by one record upon each iteration (and there by the amount of data being retieved)
	// ZIPFIAN
  	public ArrayList<Long> workload_D(String filename, List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
  		ArrayList<Long> delta_durations_D = new ArrayList<Long>();
  		
  		String retrieve_count = get_retrieve_count(filename);
  		int int_retrieve_count=Integer.parseInt(retrieve_count); 
  		
  		int number_records = dataset.size();
  		
  		// eg: records = 100, retrieve_count = 75
  		// call 'saveCourse' and 'getHome' HTTP requests for retrieve_count # of times
  		if(number_records >= int_retrieve_count)
  		{ 
  			for (int i = 0; i < int_retrieve_count; i++) {
	    			System.out.println(i);
	    	
  				// get current record (i^th)
  				List<String> record = new ArrayList<String>(4);
  				record = dataset.get(i);
  				System.out.println(record);
  			
  				// separate current record to Course attributes
  				String course_code = record.get(0);
  				String course_name = record.get(1);
  				String lecturer = record.get(2);
  				String credits = record.get(3);
  			
  				// Create new course record in database
  				http_req.sendPOST_save_course(course_code, course_name, lecturer, credits);
  				
  				// call homepage HTTP request
  				http_req.sendGET_home();
			}
		}
		// eg: records = 75, retrieve_count = 100
		// call 'saveCourse' and 'getHome' HTTP requests for records # of times
		// and call 'getHome' HTTP request for (count-records) # of times
  		else
  		{
  			for (int i = 0; i < number_records; i++) {
	    			System.out.println(i);
	    	
  				// get current record (i^th)
  				List<String> record = new ArrayList<String>(4);
  				record = dataset.get(i);
  				System.out.println(record);
  			
  				// separate current record to Course attributes
  				String course_code = record.get(0);
  				String course_name = record.get(1);
  				String lecturer = record.get(2);
  				String credits = record.get(3);
  			
  				// Create new course record in database
  				http_req.sendPOST_save_course(course_code, course_name, lecturer, credits);
  				
  				// call homepage HTTP request
  				http_req.sendGET_home();
			}
			
			for ( int j = 0; j < (int_retrieve_count - number_records); j++) {
	    			System.out.println(j);
	    			http_req.sendGET_home();
			}
  		}
  		
  		return delta_durations_D;
 	}
 	
 	// serverless invocation to return all course ID values
 	// (we need this to call retrieves, updates and deletes)
 	public List<String> get_cid_list(HTTPRequestGenerator http_req) throws Exception
  	{
  		HttpClient client = HttpClient.newHttpClient();  		
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
            		response_body = response_body.replaceAll(", $", "");
            		System.out.println(response_body);
            		
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
            		List<String> cid_list = new ArrayList<String>();

            		// n is defined to iterate over objects
            		int n = 1;

            		// loop to extract course ID of each record
            		for (int i = 0; i < array_size; i = i + class_size) {
                		cid_list.add(array_response[i]); }
                	
			return cid_list;
        	} catch (Exception e) {
        		List<String> exception_list = new ArrayList<String>();
            		exception_list.add(e.toString());
            		return exception_list;
        	}	    	
 	}
 	
 	// workload_E (DELETE_ONLY)
 	// 100% Delete
 	// Delete all existing courses from database
	// ZIPFIAN
  	public ArrayList<Long> workload_E(HTTPRequestGenerator http_req) throws Exception
  	{
  		ArrayList<Long> delta_durations_E = new ArrayList<Long>();
  		
  		List<String> cid_list = get_cid_list(http_req);

	    	System.out.println(cid_list);
	    	
	    	// send DELETE request for each cid in database
	    	for (int i = 0; i < cid_list.size(); i++) {
 			String cid = cid_list.get(i).trim();
 			
 			// MONITORING: get start time for HTTP request
  			Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
  			long start_time = timestamp_start.getTime();
  			
 			http_req.sendGET_delete_course(cid);
 			
 			// MONITORING: get end time for HTTP request
			Timestamp timestamp_end = new Timestamp(System.currentTimeMillis());
  			long end_time = timestamp_end.getTime();
  			
  			// MONITORING: calculate time to completion of HTTP request and add to output ArrayList
  			long delta_duration = end_time - start_time;
  			delta_durations_E.add(delta_duration);
		}
		
		return delta_durations_E;
 	}
 	
 	// workload_F (CREATE_AND_DELETE)
 	// 50% Create, 50% Delete (assuming that there were no other records in database)
 	// (existing records are also deleted)
 	// Insert and delete a set of courses from database
	// ZIPFIAN
  	public ArrayList<Long> workload_F(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
  		ArrayList<Long> delta_durations_F = new ArrayList<Long>();
  		
  		// create new records for each row in dataset
  		int number_records = dataset.size();
	    	for (int i = 0; i < number_records; i++) {
	    		System.out.println(i);
	    	
  			// get current record (i^th)
  			List<String> record = new ArrayList<String>(4);
  			record = dataset.get(i);
  			System.out.println(record);
  			
  			// separate current record to Course attributes
  			String course_code = record.get(0);
  			String course_name = record.get(1);
  			String lecturer = record.get(2);
  			String credits = record.get(3);
  			
  			// Create new course record in database
  			http_req.sendPOST_save_course(course_code, course_name, lecturer, credits);
		}
		
		// get cid list by calling getAllCourses serverless function
		List<String> cid_list = get_cid_list(http_req);

	    	System.out.println(cid_list);
	    	
	    	// delete all cid in database
	    	for (int i = 0; i < cid_list.size(); i++) {
 			String cid = cid_list.get(i).trim();
 			http_req.sendGET_delete_course(cid);
		}
		
		return delta_durations_F;
 	}
 	
 	// workload_G (RETRIEVE_AND_UPDATE)
 	// 50% retrieve record by ID, 50% Update (assuming that there are records in database)
	// ZIPFIAN
  	public ArrayList<Long> workload_G(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{	
		ArrayList<Long> delta_durations_G = new ArrayList<Long>();
		
		// get cid list by calling getAllCourses serverless function
		List<String> cid_list = get_cid_list(http_req);

	    	System.out.println(cid_list);
	    	
	    	// for all cid in database
	    	for (int i = 0; i < cid_list.size(); i++) {
 			String cid = cid_list.get(i).trim();
 			String c_code = "c_code_update_" + i; 
 			String c_name = "c_name_update_" + i; 
 			String c_lec = "c_lec_update_" + i; 
 			String c_credits = "200"; 
 			
 			// retrieve record by CID
 			http_req.sendGET_update_form(cid);
 			
 			// update record with new parameters
 			http_req.sendPOST_update_course(cid, c_code, c_name, c_lec, c_credits); 
		}
		
		return delta_durations_G;
 	}
 	
 	// workload_H (ALL_OPERATIONS)
 	// 25% Create, 25% Retrieve, 25% Update and 25% Delete
	// UNIFORM
  	public ArrayList<Long> workload_H(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{	
		ArrayList<Long> delta_durations_H = new ArrayList<Long>();
		
		// create new record for each row in dataset
		int number_records = dataset.size();
	    	for (int i = 0; i < number_records; i++) {
	    		System.out.println(i);
	    	
  			// get current record (i^th)
  			List<String> record = new ArrayList<String>(4);
  			record = dataset.get(i);
  			System.out.println(record);
  			
  			// separate current record to Course attributes
  			String course_code = record.get(0);
  			String course_name = record.get(1);
  			String lecturer = record.get(2);
  			String credits = record.get(3);
  			
  			// Create new course record in database
  			http_req.sendPOST_save_course(course_code, course_name, lecturer, credits);
		}
		
		// get cid list by calling getAllCourses serverless function
		List<String> cid_list = get_cid_list(http_req);

	    	System.out.println(cid_list);
	    	
	    	// for all cid in database
	    	for (int i = 0; i < cid_list.size(); i++) {
 			String cid = cid_list.get(i).trim();
 			String c_code = "c_code_update_" + i; 
 			String c_name = "c_name_update_" + i; 
 			String c_lec = "c_lec_update_" + i; 
 			String c_credits = "200"; 
 			
 			// retrieve record by CID
 			http_req.sendGET_update_form(cid);
 			
 			// update record with new parameters
 			http_req.sendPOST_update_course(cid, c_code, c_name, c_lec, c_credits); 
 			
 			// delete record
 			http_req.sendGET_delete_course(cid);
		}
		
		return delta_durations_H;
 	}
	
	// method to read and extract data set input to array
	public List<List<String>> read_dataset(String filename) throws Exception 
	{
    		List<List<String>> records = new ArrayList<List<String>>();
		try (CSVReader csvReader = new CSVReader(new FileReader(filename), ',', '\'', 1);) {
    			String[] values = null;
    			while ((values = csvReader.readNext()) != null) {
        			records.add(Arrays.asList(values));
    			}
		}
		return records;
	}
}
