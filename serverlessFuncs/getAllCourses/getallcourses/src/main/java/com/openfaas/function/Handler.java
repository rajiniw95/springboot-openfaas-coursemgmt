package com.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;

import java.sql.*;

import java.util.*;

public class Handler extends com.openfaas.model.AbstractHandler {

    public IResponse Handle(IRequest req) {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/coursedb?useSSL=false";
        String username = "root";
        String password = "rajiniw95";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM courses";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            //getting the column type
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
