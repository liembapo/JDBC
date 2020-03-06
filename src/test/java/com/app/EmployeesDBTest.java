package com.app;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DBUtility;
import utilities.DbType;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeesDBTest {

    //connect to  oracle DB
    //run following sql
    //select * from employees where jod_id = 'IT_PROG';
    //more than 0 records should be returned

    @Test
    public void countTest() throws SQLException {

        DBUtility.establishConnection(DbType.ORACLE);
        int rowCount = DBUtility.getRowsCount("select * from employees where job_id = 'IT_PROG'");
        Assert.assertTrue(rowCount > 0);
        DBUtility.closeConnection();
    }

    @Test
    public void nameTestById() throws SQLException {

        //connect to DB
        //print full name for emp with emp_id 105 it shud be david austin

        DBUtility.establishConnection(DbType.ORACLE);
        List<Map<String, Object>> name = DBUtility.runSQLQuery("SELECT first_name,last_name,salary FROM employees WHERE employee_id = 105"  );

        Assert.assertEquals(name.get(0).get("FIRST_NAME")+" " + name.get(0).get("LAST_NAME"),"David Austin");




    }
}
