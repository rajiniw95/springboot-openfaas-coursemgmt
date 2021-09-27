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
            String cid = req.getBody(); 
            String q = "INSERT INTO courses (course_code, course_name, lecturer, credits) VALUES ('CS1005', 'Research Methods', 'Stacy Peters', '100')";
            
            Statement statement = connection.createStatement();
            int executed = statement.executeUpdate(q);

            Integer executed_int = new Integer(executed);
            String executed_string = executed_int.toString();
            
            Response res = new Response();
            res.setBody(executed_string);

            return res;

        } catch (Exception e) {
            Response res = new Response();
            res.setBody(e.toString());
            e.printStackTrace();
            return res;
        }

    }
}
