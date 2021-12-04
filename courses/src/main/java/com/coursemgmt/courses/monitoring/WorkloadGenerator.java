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

import com.coursemgmt.courses.monitoring.HTTPRequestGenerator;

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
  	
  	// method to extract workload_type from user input file
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
  	
  	// method to run the user defined workload 
  	public void run_workload(String filename, String workload_type, List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
  		workload_type = workload_type.trim();
  		
    		if (workload_type.equals("workload_A")) {
    			workload_A(dataset, http_req);
    		}
    		
    		if (workload_type.equals("workload_B")) {
    			workload_B(dataset, http_req);
    		}
    		
    		if (workload_type.equals("workload_C")) {
    			workload_C(filename, http_req);
    		}

    		if (workload_type.equals("workload_D")) {
    			workload_D(filename, dataset, http_req);
    		}
    		
    		if (workload_type.equals("workload_E")) {
    			workload_E(dataset, http_req);
    		}   	
 	}
 	
 	// workload_A (CREATE HEAVY)
 	// 50% HTTP with no invokations, 50% CREATE
 	// load new course form and then save new course (repeated for all records in dataset)  
 	// follows the natural order of HTTP request (new course form --> enter data to form --> save new course)	
  	public void workload_A(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
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
 	}
 	
	// workload_B (CREATE_ONLY)
 	// 100% CREATE
 	// send HTTP request for save new course (repeated for all records in database)  	
  	public void workload_B(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
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
 	}
 	
 	// workload_C (RETRIEVE_ONLY_STATIC)
 	// 100% RETRIEVE when Database size is static
 	// send HTTP request for get home page (repeated for retrieve_count)  	
  	public void workload_C(String filename, HTTPRequestGenerator http_req) throws Exception
  	{
  		String retrieve_count = get_retrieve_count(filename);
  		int int_retrieve_count=Integer.parseInt(retrieve_count); 
  		
  		// call homepage HTTP request for 'retrieve_count' number of times
	    	for (int i = 0; i < int_retrieve_count; i++) {
	    		System.out.println(i);
	    		http_req.sendGET_home();
		}
 	}
 	
 	// workload_D (RETRIEVE_DYNAMIC)
 	// 100% RETRIEVE as the Database size grows
 	// if number_records >= int_retrieve_count --> then call save+get for retrieve_count # of times
 	// if number_records < int_retrieve_count --> then call save+get for number_records # of times AND get for delta number of times
 	// We increase the size of the database by one record upon each iteration (and there by the amount of data being retieved)
  	public void workload_D(String filename, List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
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
  	public void workload_E(List<List<String>> dataset, HTTPRequestGenerator http_req) throws Exception
  	{
  		List<String> cid_list = get_cid_list(http_req);

	    	System.out.println(cid_list);
	    	
	    	for (int i = 0; i < cid_list.size(); i++) {
 			String cid = cid_list.get(i).trim();
 			http_req.sendGET_delete_course(cid);
		}
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
