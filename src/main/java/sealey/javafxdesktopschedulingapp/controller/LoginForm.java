package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sealey.javafxdesktopschedulingapp.helpers.Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginForm implements Initializable
{
    Stage stage;
    Parent scene;
    @FXML
    private Button exitButton;
    @FXML
    private Label headerText;
    @FXML
    private Label locationLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signinButton;
    @FXML
    private Label subheaderText;
    @FXML
    private TextField usernameField;

    @FXML
    void onActionExit(ActionEvent event) {

    }
    @FXML
    void onActionOpenDashboard(ActionEvent e) throws IOException {
        Helpers.setStage("Dashboard.fxml", "Employee Dashboard", signinButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialized");
    }
}
