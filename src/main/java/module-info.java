module sealey.javafxdesktopschedulingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sealey.javafxdesktopschedulingapp.helpers to javafx.base;

    opens sealey.javafxdesktopschedulingapp to javafx.fxml;
    opens sealey.javafxdesktopschedulingapp.controller to javafx.base, javafx.fxml;
    opens sealey.javafxdesktopschedulingapp.model to javafx.base;
    opens sealey.javafxdesktopschedulingapp.dao to javafx.base;
    exports sealey.javafxdesktopschedulingapp;
    exports sealey.javafxdesktopschedulingapp.controller;
}