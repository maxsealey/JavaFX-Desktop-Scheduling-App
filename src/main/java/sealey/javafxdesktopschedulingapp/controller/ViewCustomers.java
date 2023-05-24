package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sealey.javafxdesktopschedulingapp.helpers.Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class ViewCustomers implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    private Button newApptButton;
    @FXML
    private Button updateButton;

    /**
     * Returns user to dashboard
     *
     * @param event Back button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        Helpers.setStage("Dashboard.fxml", "Employee Dashboard", backButton);
    }

    /**
     * Takes user to AddAppointment page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionNewAppt(ActionEvent event) throws IOException {
        Helpers.setStage("AddAppointment.fxml", "Add New Appointment", newApptButton);
    }

    /**
     * Takes user to ModifyAppointment page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {
        Helpers.setStage("ModifyAppointment.fxml","Update Appointment", updateButton);
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
