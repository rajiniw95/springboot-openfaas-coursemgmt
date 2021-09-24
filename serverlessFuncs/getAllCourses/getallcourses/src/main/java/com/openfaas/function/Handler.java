package com.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;

//import com.mysql.jdbc.*;

import java.sql.*;
import java.util.*;

public class Handler extends com.openfaas.model.AbstractHandler {

    static Connection connection;

    public Handler() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://mysql:3306/coursedb", "root", "rajiniw95");
            //System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("NOT Connected");
        }
    }

    public IResponse Handle(IRequest req) {

        try {
            Handler handler = new Handler();
            //System.out.println("MAIN");

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from courses");

            // print all data in table
            ArrayList<String> arrayList = new ArrayList<String>();
            while (resultset.next()) {
                int i = 1;
                while (i <= 4) {
                    arrayList.add(resultset.getString(i++));
                }
                //System.out.println(resultset.getString("course_code"));
                //System.out.println(resultset.getString("course_name"));
                //System.out.println(resultset.getString("lecturer"));
                //System.out.println(resultset.getString("credits"));
            }

            // print number of columns
            ResultSetMetaData rsmd = resultset.getMetaData();

            int column_count = rsmd.getColumnCount();

            Integer column_count_int = new Integer(column_count);

            String column_count_string = column_count_int.toString();

            //System.out.println(column_count_string);

            Response res = new Response();
            res.setBody("okay");

            return res;

        } catch (Exception e) {
            Response res = new Response();
            res.setBody(e.toString());
            e.printStackTrace();
            return res;
        }

    }
}
