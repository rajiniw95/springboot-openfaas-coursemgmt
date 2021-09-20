package com.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.*;

public class Handler extends com.openfaas.model.AbstractHandler {

    public IResponse Handle(IRequest req) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/coursedb", "root", "rajiniw95");

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from courses");

            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();

            String column_count_str = String.valueOf(column_count);
	/*
	List allRows = new ArrayList();
    	while(rs.next()){
        	String[] currentRow = new String[numberColumns];
        	for(int i = 1;i<=numberColumns;i++){
            	row[i-1]=rs.getString(i);
        	}
        	rows.add(row);
    	}
*/
            Response res = new Response();
            res.setBody(column_count_str);

            return res;

        } catch (Exception e) {
            Response res = new Response();
            res.setBody(e.toString());

            return res;
        }

    }
}
