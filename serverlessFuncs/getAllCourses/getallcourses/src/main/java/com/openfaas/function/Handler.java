package com.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;

import com.mysql.jdbc.*;
import java.sql.*;
import java.util.*;

public class Handler extends com.openfaas.model.AbstractHandler {

    Connection connection = null;

    public void Handler() {
        //Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/coursedb", "root", "rajiniw95");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public IResponse Handle(IRequest req) {

        try {

            Handler handler = new Handler();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from courses");

            ResultSetMetaData rsmd = rs.getMetaData();

            int column_count = rsmd.getColumnCount();
            String column_count_string = Integer.toString(column_count);

//	        List allRows = new ArrayList();
//    	    while(rs.next()){
//        	    String[] currentRow = new String[column_count];
//        	    for(int i = 1;i<=column_count;i++){
//            	    row[i-1]=rs.getString(i);
//        	    }
//        	    rows.add(row);
//    	    }

            Response res = new Response();
            res.setBody(column_count_string);

            return res;

        } catch (Exception e) {
            Response res = new Response();
            res.setBody(e.toString());
            return res;
        }

    }
}
