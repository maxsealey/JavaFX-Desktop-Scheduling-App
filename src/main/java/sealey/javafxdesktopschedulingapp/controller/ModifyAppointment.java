package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sealey.javafxdesktopschedulingapp.helpers.Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAppointment implements Initializable
{

    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Helpers.setStage("Dashboard.fxml", "Employee Dashboard", cancelButton);
    }
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        Helpers.setStage("Dashboard.fxml", "Employee Dashboard", saveButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}