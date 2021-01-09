package com.student.grants.management.controllers;

import spark.Request;
import spark.Response;

public class MainController {

    public static String working(Request request, Response response) {

        return "{\"status\":\"working\"}";
    }
}
