package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class ModifyAppointment implements Initializable
{

    @FXML
    private Button cancelButton;
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
        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", cancelButton);
    }

    /**
     * Saves customer data, returns user to dashboard
     *
     * @param event Save button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", saveButton);
    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}