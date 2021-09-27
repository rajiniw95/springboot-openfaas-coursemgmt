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
            String q = "SELECT * FROM courses WHERE cid = " + cid;
            
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(q);

            // get number of columns
            ResultSetMetaData rsmd = resultset.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            // put all data in table to array list 
            ArrayList<String> arrayList = new ArrayList<String>();
            while (resultset.next()) {
                int i = 2;
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
