package sealey.javafxdesktopschedulingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sealey.javafxdesktopschedulingapp.util.JDBC;

import java.io.IOException;

/**
 * @author Max Sealey
 * */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("GMS Virtual Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
