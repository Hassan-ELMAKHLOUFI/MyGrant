package com.student.grants.management;

import com.student.grants.management.database.Database;
import com.student.grants.management.routes.Routes;

public class Application {
    public static void main(String[] args) {
        Database.connect();
        Routes.routes();
    }
}
