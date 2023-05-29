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
import sealey.javafxdesktopschedulingapp.helpers.Helpers;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Description:
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

    @FXML
    void onActionExit(ActionEvent event) {

    }

    /**
     * Upon verification takes user to dashboard
     *
     * @param event Back button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionOpenDashboard(ActionEvent event) throws IOException {
        Helpers.setStage("Dashboard.fxml", "Employee Dashboard", signinButton);
    }

    private void setAllLabels(ResourceBundle bundle){
        exitButton.setText(bundle.getString(String.valueOf(exitButton)));
        passwordField.setPromptText(bundle.getString(String.valueOf(passwordField)));
        signinButton.setText(bundle.getString(String.valueOf(signinButton)));
        subheaderText.setText(bundle.getString(String.valueOf(subheaderText)));
        usernameField.setPromptText(bundle.getString(String.valueOf(usernameField)));
    }

    private void setLocationLabel(){
        try {
            ResourceBundle bundle;
            if(locale.getLanguage().equals("fr")){
                bundle = ResourceBundle.getBundle("lang_FR", locale);
            } else {
                bundle = ResourceBundle.getBundle("lang_EN", locale);
            }
            locationLabel.setText(bundle.getString(String.valueOf(locationLabel)) + ": " + String.valueOf(zoneId));
        } catch (MissingResourceException e) {
            System.out.printf(" Error setting location label: " + e);
        }
    }

    private void frenchTranslation(){
        try {
            ResourceBundle bundle;
            if(locale.getLanguage().equals("fr")){
                bundle = ResourceBundle.getBundle("lang_FR", locale);
                setAllLabels(bundle);
            } else {
                bundle = ResourceBundle.getBundle("lang_EN", locale);
                setAllLabels(bundle);
            }
        } catch (MissingResourceException e) {
            System.out.println(" Error with translation: " + e);
        }
    }

    private void authentication(String username, String password) {

    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.locale = Locale.getDefault();
        this.zoneId = ZoneId.systemDefault();

        setLocationLabel();
        frenchTranslation();
    }
}
