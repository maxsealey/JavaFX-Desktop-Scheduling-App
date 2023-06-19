package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class AddCustomer implements Initializable
{
    @FXML
    private TextField IDTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> countryComboBox;

    @FXML
    private ComboBox<?> fldComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private Button saveButton;

    /**
     * Returns user to dashboard without saving
     *
     * @param event Cancel button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmCancel()){
                FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", cancelButton);
            }
        } catch(NoSuchElementException e) {
            System.out.println("cancel cancel");
        }
    }

    /**
     * Saves customer data, returns user to dashboard
     *
     * @param event Save button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmSave()){
                // add
                FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", saveButton);
            }
        } catch (NoSuchElementException e){
            System.out.println("cancel save");
        }
    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //IDTextField.setText("1");
    }
}
