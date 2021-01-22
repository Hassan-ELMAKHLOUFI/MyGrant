package com.myorientation;

import android.util.Log;

import java.sql.*;

public class Database {
    public static Connection connection = null;

    public static void connect(String host, String port,String name, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+name, user, password);
            Log.d("ensetm","connected successfully to database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        Statement stmt;
        ResultSet resultSet = null;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (resultSet == null) ;
        return resultSet;

    }
}
