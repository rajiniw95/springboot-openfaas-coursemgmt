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
            connection = DriverManager.getConnection("jdbc:mysql://mysql:3306/coursedb", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IResponse Handle(IRequest req) {

        try {
            //Handler handler = new Handler();

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from courses");

            // get all data in table
            ArrayList<String> arrayList = new ArrayList<String>();
            while (resultset.next()) {
                int i = 1;
                while (i <= 4) {
                    arrayList.add(resultset.getString(i++));
                }
            }

            // get number of columns
            ResultSetMetaData rsmd = resultset.getMetaData();

            int column_count = rsmd.getColumnCount();

            Integer column_count_int = new Integer(column_count);

            String column_count_string = column_count_int.toString();

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
