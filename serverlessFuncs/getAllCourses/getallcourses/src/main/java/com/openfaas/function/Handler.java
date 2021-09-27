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
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM courses");

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
            
	    // convert column count integer to string 
	    // so that it can be included in the response body -- if necessary
            // Integer column_count_int = new Integer(column_count);
            // String column_count_string = column_count_int.toString();

            Response res = new Response();
            res.setBody(str);

            return res;

        } catch (Exception e) {
            Response res = new Response();
            res.setBody(e.toString());
            e.printStackTrace();
            return res;
        }

    }
}
