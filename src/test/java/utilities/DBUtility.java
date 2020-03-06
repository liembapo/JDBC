package utilities;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public static void establishConnection(DbType dbType) throws SQLException {
        switch (dbType){
            case ORACLE:
                connection = DriverManager.getConnection(Configur.getProperty("oracledb.url"),
                                                        Configur.getProperty("oracledb.user"),
                                                        Configur.getProperty("oracledb.password"));
        }
    }

    public static List<Map<String,Object>> runSQLQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sql);

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
        return list;
    }

    public static void closeConnection(){
        try {
            if(resultSet != null){resultSet.close();}
            if(statement != null){statement.close();}
            if(connection!= null) connection.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static int getRowsCount(String sqql) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sqql);

        resultSet.last();
        return resultSet.getRow();
    }
}

