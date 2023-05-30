package sealey.javafxdesktopschedulingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;

import java.io.IOException;
import java.util.Locale;

/**
 * Description: The Main class sets the initial scene and launches the application.
 *
 * @author Max Sealey
 * */
public class Main extends Application {

    /**
     * The start() method retrieves the FXML file for the main window and sets the scene. Calls the testData method, which can be removed or replaced.
     *
     * @param stage Stage to contain the scenes
     * @throws IOException Catches issues and displays an error message if there is a problem starting the program
     * */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("GVS");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is used to run the program.
     *
     * @param args String arguments that may be passed in. Will not be utilized in this program.
     * */
    public static void main(String[] args) {
        //For testing french labels:
        //Locale.setDefault(new Locale("fr"));

        DBConnection.openConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}
