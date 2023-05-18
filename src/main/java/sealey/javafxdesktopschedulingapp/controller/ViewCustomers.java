package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sealey.javafxdesktopschedulingapp.helpers.Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewCustomers implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private Button newApptButton;
    @FXML
    private Button updateButton;

    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        Helpers.setStage("Dashboard.fxml", "Employee Dashboard", backButton);
    }
    @FXML
    void onActionNewAppt(ActionEvent event) throws IOException {
        Helpers.setStage("AddAppointment.fxml", "Add New Appointment", newApptButton);
    }
    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {
        Helpers.setStage("ModifyAppointment.fxml","Update Appointment", updateButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
