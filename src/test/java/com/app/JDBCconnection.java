package com.app;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCconnection {

    String oracleDbUrl = "jdbc:oracle:thin:@ec2-3-16-138-3.us-east-2.compute.amazonaws.com:1521:xe";
    String oracleDbUsername = "HR";
    String oracleDbPassword = "hr";

    @Test
    public void oracleJDBC() throws SQLException {
        Connection connection = DriverManager.getConnection(oracleDbUrl,oracleDbUsername,oracleDbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from countries");

//        for (int i = 1; i < 5; i++) {
//
//            resultSet.next();
//            System.out.println(resultSet.getString(1));
//            System.out.println(resultSet.getString("country_name"));
//            System.out.println(resultSet.getString("region_id"));
//        }
//          or
        while (resultSet.next()){
            System.out.print (resultSet.getString(1)+"-") ;
            System.out.print(resultSet.getString("country_name" )+ "-");
            System.out.println(resultSet.getString("region_id"));

        }


        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void oracleJDBC2() throws SQLException {
        Connection connection = DriverManager.getConnection(oracleDbUrl,oracleDbUsername,oracleDbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from countries");

        resultSet.first();
        System.out.println(resultSet.getRow());

        resultSet.last();
        System.out.println(resultSet.getRow());

        //after last we have to usebeforeFirst to get back into starting point\

        resultSet.beforeFirst();
        resultSet.next();
        System.out.println(resultSet.getRow());

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metaData() throws SQLException {
        Connection connection = DriverManager.getConnection(oracleDbUrl,oracleDbUsername,oracleDbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");

        String sql = "select first_name, last_name , job_id, salary from employees";

        //database metaData
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("User : " + dbMetaData.getUserName());
        System.out.println("Database Type : " + dbMetaData.getDatabaseProductName());

        //resultSet metadata
        ResultSetMetaData dbMeData = resultSet.getMetaData();
        System.out.println("Column count : " + dbMeData.getColumnCount());
        System.out.println("Column 1 name is : " + dbMeData.getColumnName(1));
        System.out.println("\n");
        for (int i = 1; i <= dbMeData.getColumnCount(); i++) {
            System.out.println( "column " +i+ " :" +dbMeData.getColumnName(i) );
        }

        //Storing a metadata to list map

            List<Map<String,Object>> list = new ArrayList<  >();
            ResultSetMetaData rsMData = resultSet.getMetaData();

        int colcount = rsMData.getColumnCount();

        while (resultSet.next()){

            Map<String,Object> rowMap = new HashMap<>();

            for (int col = 1; col <colcount ; col++) {
                rowMap.put(rsMData.getColumnName(col),resultSet.getObject(col));
            }
            list.add(rowMap);
        }

        //print all the employees id

        for (Map<String ,Object > emp: list) {

            System.out.println(emp.get("EMPLOYEE_ID"));

        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
