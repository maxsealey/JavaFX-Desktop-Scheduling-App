module sealey.javafxdesktopschedulingapp {
    requires javafx.controls;
    requires javafx.fxml;


    //opens sealey.javafxdesktopschedulingapp.model to javafx.base;
    opens sealey.javafxdesktopschedulingapp.util to javafx.base;
    exports sealey.javafxdesktopschedulingapp;
    opens sealey.javafxdesktopschedulingapp to javafx.base, javafx.fxml;
}