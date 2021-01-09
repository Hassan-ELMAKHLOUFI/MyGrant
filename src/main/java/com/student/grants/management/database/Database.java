package com.student.grants.management.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Database {
    public static Connection connection = null;

    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            InputStream input = Database.class.getClassLoader().getResourceAsStream("properties.properties");
            Properties properties = new Properties();
            properties.load(input);
            String name = properties.getProperty("database.name");
            String host = properties.getProperty("database.host");
            String port = properties.getProperty("database.port");
            String user = properties.getProperty("database.user");
            String password = properties.getProperty("database.password");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + name, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (connection == null) ;
    }
}
