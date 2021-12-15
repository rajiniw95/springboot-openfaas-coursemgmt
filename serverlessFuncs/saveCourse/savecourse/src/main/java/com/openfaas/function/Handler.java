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
            connection = DriverManager.getConnection("jdbc:mysql://10.43.0.2:3306/coursedb", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IResponse Handle(IRequest req) {
        try {
            Map<String, String> query = req.getQuery();
            String req_string = query.get("data");
            req_string = req_string.replaceAll("%20", " ");
            
            String[] req_array = req_string.split("\\s*,\\s*");
            String course_code = req_array[0];
            String course_name = req_array[1];
            String lecturer = req_array[2];
            String credits = req_array[3];      
            
            String q = "INSERT INTO courses (course_code, course_name, lecturer, credits) VALUES ('" + course_code + "', '" + course_name + "', '" + lecturer + "', '" + credits + "')";
            
            // MONITORING: get start time for serverless invocation
            Timestamp timestamp_start = new Timestamp(System.currentTimeMillis());
            long start_time = timestamp_start.getTime();
            
            Statement statement = connection.createStatement();
            int executed = statement.executeUpdate(q);
            
            // MONITORING: get end time for serverless invocation
            Timestamp timestamp_end = new Timestamp(System.currentTimeMillis()); 
            long end_time = timestamp_end.getTime();
            
            // MONITORING: calculate time to completion of serverless invocation and add to global ArrayList
            long db_latency = end_time - start_time;
            String str_db_latency = String.valueOf(db_latency);

            Integer executed_int = new Integer(executed);
            String executed_string = executed_int.toString();
            
            // append DB latency to beginning of response (comma separated from 'executed')
            String executed_latency = str_db_latency + "," + executed_string;
            
            Response res = new Response();
            res.setBody(executed_latency);

            return res;

        } catch (Exception e) {
            Response res = new Response();
            res.setBody(e.toString());
            e.printStackTrace();
            return res;
        }

    }
}
