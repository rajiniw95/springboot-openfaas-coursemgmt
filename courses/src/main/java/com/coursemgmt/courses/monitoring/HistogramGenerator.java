package com.coursemgmt.courses.monitoring;

import org.HdrHistogram.*;

import java.net.DatagramSocket;
import java.net.SocketException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;

import java.sql.Timestamp;  
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

public class HistogramGenerator {
   	
   	static Histogram histogram = new Histogram(TimeUnit.SECONDS.toNanos(1), 3);
	// (max recorded value, decimal point precision)             
    	
    	public static void create_histogram(String workload_type, String request_type, String output_histogram_location, ArrayList<Long> delta_durations) throws IOException{        
        	long startTime = System.currentTimeMillis();
        	long now;
        	
        	Timestamp timestamp_filename = new Timestamp(System.currentTimeMillis());
  		long time_filename = timestamp_filename.getTime();
  		String str_timestamp_filename = String.valueOf(time_filename);

        	// define file name of output desitination
        	String filename = workload_type + "_" + request_type + "_" + str_timestamp_filename + ".txt";    
        	String filepath = output_histogram_location + filename;  
        	
        	for(int i = 0; i < delta_durations.size(); i++)
		{
    			histogram.recordValue(delta_durations.get(i));
		}	 
        	       	  		
  		FileOutputStream fos = new FileOutputStream(filepath);  
       
        	// Write Output Percentile Distribution to PrintStream
        	try(PrintStream ps = new PrintStream(fos)){
        		ps.println("Recorded latencies [in milli seconds]:");
        		ps.println("Workload Type : " + workload_type);
        		ps.println("Request/ Invocation/ DB Operation Type : " + request_type);
        		ps.println("Recorded completion times :");
        		ps.println(delta_durations);
        		ps.println();
        		ps.println("Histogram Distribution : ");
            		histogram.outputPercentileDistribution(ps, 1.0);
            		ps.println();
            		ps.println("50th Percentile : ");
            		ps.println(histogram.getValueAtPercentile(50));
            		ps.println("90th Percentile : ");
            		ps.println(histogram.getValueAtPercentile(90));
            		ps.println("95th Percentile : ");
            		ps.println(histogram.getValueAtPercentile(95));
            		ps.println("99th Percentile : ");
            		ps.println(histogram.getValueAtPercentile(99));
            		ps.println("99.9th Percentile : ");
            		ps.println(histogram.getValueAtPercentile(99.9));
            		ps.println("99.99th Percentile : ");
            		ps.println(histogram.getValueAtPercentile(99.99));
            		ps.println("99.999th Percentile : ");
            		ps.println(histogram.getValueAtPercentile(99.999));
            		ps.flush();
        	} catch (Exception e) {
            		e.printStackTrace();
        	}
        
        	System.out.println("Self Scaling Histogram Created and Analyzed");
        	
        	// reset the histogram so that the next function call starts from a fresh histogram 
        	// (important since we would be calling this same function for multiple request types of same workload)
        	histogram.reset();
    	}
}
