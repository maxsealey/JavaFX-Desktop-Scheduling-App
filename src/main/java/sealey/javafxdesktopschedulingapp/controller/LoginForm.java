package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sealey.javafxdesktopschedulingapp.dao.UserQuery;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Description: Controller for the initial log-in scene.
 *
 * @author Max Sealey
 * */
public class LoginForm implements Initializable
{
    Stage stage;
    Parent scene;
    @FXML
    private Button exitButton;
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

    private Locale locale;
    private ZoneId zoneId;

    /**
     * Closes and exits the program
     * */
    @FXML
    private void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Upon verification takes user to dashboard
     *
     * @param event Back button event
     * @throws IOException IOException
     * */
    @FXML
    private void onActionOpenDashboard(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            if(UserQuery.validateCredentials(username, password) > 0){
                FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", signinButton);
            } else {
                System.out.println("wrong username or password");
                Alerts.invalidCredentials();
            }
        } catch(Exception e) {
            System.out.println("Something went wrong opening dashboard: " + e);
        }
    }

    /**
     * Sets all labels, including locale, in either English or French depending
     * on user setting
     * */
    private void setLabels(){
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("lang", locale);

            exitButton.setText(bundle.getString("exitButton"));
            passwordField.setPromptText(bundle.getString("passwordField"));
            signinButton.setText(bundle.getString("signinButton"));
            subheaderText.setText(bundle.getString("subheaderText"));
            usernameField.setPromptText(bundle.getString("usernameField"));
            locationLabel.setText(bundle.getString("locationLabel") + ": " + String.valueOf(zoneId));
        } catch (MissingResourceException e){
            System.out.println("Missing bundle error: " + e);
        }
    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        locale = Locale.getDefault();
        zoneId = ZoneId.systemDefault();

        setLabels();
    }
}
