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

    	// A Histogram covering the range from 1 nsec to 1 hour with 3 decimal point resolution:
   	// static Histogram histogram = new Histogram(3600000000000L, 3);
   	
   	static Histogram histogram = new Histogram(TimeUnit.SECONDS.toNanos(1), 3);
	// (max recorded value, decimal point precision)             

    	static public volatile DatagramSocket socket;

    	static long WARMUP_TIME_MSEC = 5000;
    	static long RUN_TIME_MSEC = 20000;


    	static void recordTimeToCreateAndCloseDatagramSocket() {
        	long startTime = System.nanoTime();
        	try {
            		socket = new DatagramSocket();
        	} catch (SocketException ex) {
        	} finally {
            		socket.close();
        	}
        	long endTime = System.nanoTime();
        	histogram.recordValue(endTime - startTime);
    	}

    	public static void create_histogram_test() {        
        	long startTime = System.currentTimeMillis();
        	long now;

        	do {
            		recordTimeToCreateAndCloseDatagramSocket();
            		now = System.currentTimeMillis();
        	} while (now - startTime < WARMUP_TIME_MSEC);

        	histogram.reset();

        	do {
            		recordTimeToCreateAndCloseDatagramSocket();
            		now = System.currentTimeMillis();
        	} while (now - startTime < RUN_TIME_MSEC);
        
        	// define file name of output desitination
        	String filename = "histogram_output_testing.txt";     
        
        	// Write Output Percentile Distribution to PrintStream
        	try(PrintStream ps = new PrintStream(filename)){
        		ps.println("Recorded latencies [in sec] for Create+Close of a DatagramSocket:");
            		histogram.outputPercentileDistribution(ps, 1000.0);
            		ps.println("UPDATE APPLIED");
            		ps.flush();
        	} catch (FileNotFoundException e) {
            		e.printStackTrace();
        	}
        
        	System.out.println("Self Scaling Histogram Created and Analyzed");
    	}
    	
    	public static void create_histogram(String workload_type, String output_histogram_location, ArrayList<Long> delta_durations) throws IOException{        
        	long startTime = System.currentTimeMillis();
        	long now;
        	
        	Timestamp timestamp_filename = new Timestamp(System.currentTimeMillis());
  		long time_filename = timestamp_filename.getTime();
  		String str_timestamp_filename = String.valueOf(time_filename);

        	// define file name of output desitination
        	String filename = workload_type + "_" + str_timestamp_filename + ".txt";    
        	String filepath = output_histogram_location + filename;  
        	
        	for(int i = 0; i < delta_durations.size(); i++)
		{
    			histogram.recordValue(delta_durations.get(i));
		}	 
        	       	  		
  		FileOutputStream fos = new FileOutputStream(filepath);  
       
        	// Write Output Percentile Distribution to PrintStream
        	try(PrintStream ps = new PrintStream(fos)){
        		ps.println("Recorded latencies [in milli seconds] for HTTP requests :");
        		ps.println("Workload Type : " + workload_type);
        		ps.println("Recorded HTTP request completion times :");
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
            		ps.flush();
        	} catch (Exception e) {
            		e.printStackTrace();
        	}
        
        	System.out.println("Self Scaling Histogram Created and Analyzed");
    	}
}
