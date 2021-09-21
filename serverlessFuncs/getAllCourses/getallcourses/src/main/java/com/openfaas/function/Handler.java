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
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/coursedb", "root", "rajiniw95");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public IResponse Handle(IRequest req) {

        try {
            Statement statement = connection.createStatement();/*

            ResultSet rs = statement.executeQuery("select * from courses");

            ResultSetMetaData rsmd = rs.getMetaData();

            int column_count = rsmd.getColumnCount();

            Integer column_count_int = new Integer(column_count);

            String column_count_string = column_count_int.toString();*/

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
