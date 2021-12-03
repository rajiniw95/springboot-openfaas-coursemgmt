package com.coursemgmt.courses.monitoring;

import java.nio.file.*;
import java.io.*;

import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

public class WorkloadGenerator {
		
	// method to read user input file as string
  	public String read_file_as_string(String file_name)throws Exception
  	{
    		String data = "";
    		data = new String(Files.readAllBytes(Paths.get(file_name)));
    		return data;
 	}
		
	// method to extract workload_type from user input file
	public String get_workload_type(String file_name)throws Exception {
	
		// read file as string
   		String data = read_file_as_string(file_name);
    					
    		// split rows to the segments array
    		String segments[] = data.split(";");
    		
    		// string matching to find array element starting with "workload_type"
    		String workload_type = "workload_type";
    		Pattern pattern = Pattern.compile(workload_type);            			
      		Matcher matcher = pattern.matcher(workload_type);
      		String[] workload_type_user_input;
      		String defined_workload_type = "";
    		for (String s: segments) {           
    			if (matcher.find()) {
    				workload_type_user_input = s.split(" = ", 2);
  				defined_workload_type = workload_type_user_input[1]; 
			}
		}
		
    		return defined_workload_type;
  	}
  	
  	// method to run the user defined workload 
  	public void run_workload(String workload_type)
  	{
  		workload_type = workload_type.trim();
  		
    		if (workload_type.equals("workload_A")) {
    			workload_A();
    		}
    		
    		if (workload_type.equals("workload_B")) {
    			workload_B();
    		}
    		
    		if (workload_type.equals("workload_C")) {
    			workload_C();
    		}

    		if (workload_type.equals("workload_D")) {
    			workload_D();
    		}
    		
    		if (workload_type.equals("workload_E")) {
    			workload_E();
    		}   	
 	}
 	
 	// workload_A
  	public void workload_A()
  	{
	    System.out.println("Hello World from A");
 	}
 	
	// workload_B
  	public void workload_B()
  	{
	    System.out.println("Hello World from B");
 	}
 	
	// workload_C
  	public void workload_C()
  	{
	    System.out.println("Hello World from C");
 	}
 	
 	// workload_D
  	public void workload_D()
  	{
	    System.out.println("Hello World from D");
 	}
 	
 	// workload_E
  	public void workload_E()
  	{
	    System.out.println("Hello World from E");
 	}
}
