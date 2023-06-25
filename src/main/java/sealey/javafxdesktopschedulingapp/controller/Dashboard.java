package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sealey.javafxdesktopschedulingapp.dao.*;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Description: This class is the controller for the Dashboard scene, which is set when user successfully logs in.
 *
 * @author maxsealey Sealey
 * */
public class Dashboard implements Initializable
{
    @FXML
    private Button generateReportsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button newApptButton;

    @FXML
    private Button newCustomerButton;

    @FXML
    private Button sortCustomersButton;

    @FXML
    private Button viewScheduleButton;

    @FXML
    private Label welcomeText;

    /**
     * Takes user to GenerateReports page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionGenerateReport(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("GenerateReports.fxml","Generate Reports", generateReportsButton);
    }

    /**
     * Takes user to AddAppointment page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionNewAppointment(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("AddAppointment.fxml","Add New Appointment", newApptButton);
    }

    /**
     * Takes user to AddCustomer page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionNewCustomer(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("AddCustomer.fxml","Add New Customer", newCustomerButton);
    }

    /**
     * Takes user to ViewCustomers page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionSortCustomers(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("ViewCustomers.fxml","All Customers", sortCustomersButton);
    }

    /**
     * Takes user to AppointmentSchedule page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionViewSchedule(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("AppointmentSchedule.fxml","Schedule - All Appointments", viewScheduleButton);
    }

    /**
     * Takes user to LoginForm page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionLogout(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("LoginForm.fxml","GVS", logoutButton);
    }

    /**
     * Runs on initialization
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Welcome, " + UserDAO.getCurrentUser().getUsername() + "!");
    }
}
