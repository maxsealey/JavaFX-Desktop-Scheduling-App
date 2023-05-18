package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sealey.javafxdesktopschedulingapp.helpers.Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard implements Initializable
{
    Stage stage;
    Parent scene;
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
    private Button updateApptButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button viewScheduleButton;

    @FXML
    private Label welcomeText;

    @FXML
    void onActionGenerateReport(ActionEvent event) throws IOException {
        Helpers.setStage("GenerateReports.fxml","Generate Reports", generateReportsButton);
    }

    @FXML
    void onActionNewAppointment(ActionEvent event) throws IOException {
        Helpers.setStage("AddAppointment.fxml","Add New Appointment", newApptButton);
    }

    @FXML
    void onActionNewCustomer(ActionEvent event) throws IOException {
        Helpers.setStage("AddCustomer.fxml","Add New Customer", newCustomerButton);
    }

    @FXML
    void onActionSortCustomers(ActionEvent event) throws IOException {
        Helpers.setStage("ViewCustomers.fxml","All Customers", sortCustomersButton);
    }

    @FXML
    void onActionUpdateAppt(ActionEvent event) throws IOException {
        Helpers.setStage("ModifyAppointment.fxml","Update Appointment", updateApptButton);
    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        Helpers.setStage("ModifyCustomer.fxml","Update Customer Information", updateCustomerButton);
    }

    @FXML
    void onActionViewSchedule(ActionEvent event) throws IOException {
        Helpers.setStage("AppointmentSchedule.fxml","Schedule - All Appointments", viewScheduleButton);
    }

    @FXML
    void onActionLogout(ActionEvent e) throws IOException {
        Helpers.setStage("LoginForm.fxml","GMS Scheduling - Please sign-in", logoutButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialized");
    }
}
