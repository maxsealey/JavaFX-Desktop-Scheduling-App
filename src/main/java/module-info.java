module sealey.javafxdesktopschedulingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    //opens sealey.javafxdesktopschedulingapp.model to javafx.base;
    opens sealey.javafxdesktopschedulingapp.util to javafx.base;
    exports sealey.javafxdesktopschedulingapp;
    opens sealey.javafxdesktopschedulingapp to javafx.base, javafx.fxml;
    exports sealey.javafxdesktopschedulingapp.controller;
    opens sealey.javafxdesktopschedulingapp.controller to javafx.base, javafx.fxml;
}