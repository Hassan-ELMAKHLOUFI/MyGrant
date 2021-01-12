package com.student.grants.management.routes;

import com.student.grants.management.controllers.AdministratorsController;
import com.student.grants.management.controllers.MainController;
import com.student.grants.management.controllers.StudentsController;

import static spark.Spark.get;
import static spark.Spark.post;

public class Routes {
    public static void routes() {
        get("/", MainController::working);

        post("/students/login", StudentsController::login);
        post("/students/register", (req, res) -> "Hello World");
        post("/students/reset-password", (req, res) -> "Hello World");
        post("/students/update-profile", (req, res) -> "Hello World");
        post("/students/filling-information", (req, res) -> "Hello World");
        post("/students/upload-documents", (req, res) -> "Hello World");
        post("/students/extract-receipt", (req, res) -> "Hello World");

        post("/administrators/add", AdministratorsController::add);
        post("/administrators/login", (req, res) -> "Hello World");
        post("/administrators/reset-password", (req, res) -> "Hello World");
        post("/administrators/update-profile", (req, res) -> "Hello World");
        post("/administrators/universities", (req, res) -> "Hello World");
        post("/administrators/establishments", (req, res) -> "Hello World");
        post("/administrators/generate-list", (req, res) -> "Hello World");

    }
}
