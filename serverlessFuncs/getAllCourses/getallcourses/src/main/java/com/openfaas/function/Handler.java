package com.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;

import java.sql.*;
import java.util.*;

public class Handler extends com.openfaas.model.AbstractHandler {

    static Connection connection;

    public Handler() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://10.96.0.2:3306/coursedb", "root", "password");
	    // connection = DriverManager.getConnection("jdbc:mysql://10.43.0.2:3306/coursedb", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IResponse Handle(IRequest req) {
        try {
            // MONITORING: get start time for serverless invocation
            Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
            long start_time = timestamp_start.getTime();
            
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM courses");
            
            // MONITORING: get end time for serverless invocation
            Timestamp timestamp_end = new Timestamp(System.currentTimeMillis()); 
            long end_time = timestamp_end.getTime();
            
            // MONITORING: calculate time to completion of serverless invocation and add to global ArrayList
            long db_latency = end_time - start_time;
            String str_db_latency = String.valueOf(db_latency);

            // get number of columns
            ResultSetMetaData rsmd = resultset.getMetaData();

            int column_count = rsmd.getColumnCount();
            
            // put all data in table to array list 
            ArrayList<String> arrayList = new ArrayList<String>();
            while (resultset.next()) {
                int i = 1;
                while (i <= column_count) {
                    arrayList.add(resultset.getString(i++));
                }
            }
	
	    StringBuffer stringBuffer = new StringBuffer();
      
            for (String s : arrayList) {
           	stringBuffer.append(s);
           	stringBuffer.append(", ");
            }
            String str = stringBuffer.toString();
            
            // append DB latency to beginning of response (comma separated from 'executed')
            String response_with_latency = str_db_latency + "," + str;
            
	    // convert column count integer to string 
	    // so that it can be included in the response body -- if necessary
            // Integer column_count_int = new Integer(column_count);
            // String column_count_string = column_count_int.toString();

            Response res = new Response();
            res.setBody(response_with_latency);

            return res;

        } catch (Exception e) {
            Response res = new Response();
            res.setBody(e.toString());
            e.printStackTrace();
            return res;
        }

    }
}
