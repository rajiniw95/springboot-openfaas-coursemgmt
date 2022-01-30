package process_histogram;

import org.HdrHistogram.*;

import java.nio.file.*;
import java.io.*;
import java.io.PrintStream;
import java.io.FileOutputStream;

import java.sql.Timestamp;  
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

public class App {

    /* NECESSARY UPDATES
    1. Add latency input values to file (file name to be defined in App.java)
    2. App.java: workload_type, request_type, output_histogram_location, input_data_file */

    // USER INPUT: UPDATE AS REQUIRED
    public static String workload_type = "workload_D";
    public static String request_type = "DB_ALL";
    public static String output_histogram_location = "/Users/rajini/Documents/GitHub/springboot-openfaas-coursemgmt/process_histogram/output_files_processed/";
    public static String input_data_file = "/Users/rajini/Documents/GitHub/springboot-openfaas-coursemgmt/process_histogram/input_files/workload_D_DB_ALL.txt";
    public static int no_operations = 2;

    public static String read_file_as_string(String file_name)throws Exception
  	{
    		String data = "";
    		data = new String(Files.readAllBytes(Paths.get(file_name)));
    		return data;
 	}

	public static ArrayList<Long> get_latency_array(String file_name)throws Exception 
	{
   		String data = read_file_as_string(file_name);
    	String values[] = data.split(", "); 

        int values_length = values.length;
        int combined_length = values_length/no_operations;
        int[] values_combined = new int[combined_length];

        for (int i = 0; i < (values.length)/no_operations; i++) {
            int val1 = Integer.parseInt(values[i]);
            int val2 = Integer.parseInt(values[i+((values.length)/no_operations)]);
            values_combined[i] = val1 + val2;
        }

        ArrayList<Long> latency_values = new ArrayList<Long>();
        for (int i = 0; i < values_combined.length; i++) {
            latency_values.add(Long.valueOf(values_combined[i]));
        }
		return latency_values;
  	}

    static Histogram histogram = new Histogram(TimeUnit.SECONDS.toNanos(1), 3);
	// (max recorded value, decimal point precision)             
    	
    public static void create_histogram(String workload_type, String request_type, String output_histogram_location, ArrayList<Long> delta_durations) throws Exception{        
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

    public static void main(final String[] args) throws Exception {
        ArrayList<Long> histogram_latency_values = new ArrayList<Long>();
        histogram_latency_values = get_latency_array(input_data_file);
        create_histogram(workload_type, request_type, output_histogram_location, histogram_latency_values); 
    }
}
