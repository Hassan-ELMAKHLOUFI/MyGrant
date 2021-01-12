package com.student.grants.management.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alibaba.fastjson.JSON;
import com.student.grants.management.database.Database;
import com.student.grants.management.models.Administrator;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministratorsController {


    public static String login(Request request, Response response) {
        String body = request.body();
        Administrator administrator = JSON.parseObject(body, Administrator.class);
        BCrypt.Result result = BCrypt.verifyer().verify("password".toCharArray(), administrator.getPassword());
        if (result.verified) {
            //TODO
        }
        return response.body();
    }

    public static String add(Request request, Response response) {
        response.type("application/json");
        String body = request.body();
        Administrator administrator = JSON.parseObject(body, Administrator.class);
        administrator.setPassword(hashPassword(administrator.getPassword()));
        try {
            String query = "INSERT INTO administrator (firstname_a, lastname_a, phonenumber_a, email_a, password_a) VALUES (?,?,?,?,?)";
            PreparedStatement statement = Database.connection.prepareStatement(query);
            statement.setString(1, administrator.getFirstName());
            statement.setString(2, administrator.getLastName());
            statement.setString(3, administrator.getPhoneNumber());
            statement.setString(4, administrator.getEmail());
            statement.setString(5, administrator.getPassword());
            int result = statement.executeUpdate();
            if (result == 1) {
                return JSON.toJSONString(administrator);
            } else {
                //TODO
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return response.body();
    }


    private static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
