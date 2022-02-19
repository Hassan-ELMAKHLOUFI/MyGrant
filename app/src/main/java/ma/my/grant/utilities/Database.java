package ma.my.grant.utilities;

import android.util.Log;

import java.sql.*;

public class Database {
    public static Connection connection = null;

    public static void connect(String host, String port, String name, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + name, user, password);
            Log.d("My Grant", "Connected successfully to the database !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
