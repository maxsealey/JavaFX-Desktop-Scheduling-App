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
import sealey.javafxdesktopschedulingapp.dao.UserDAO;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
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

    private static String currentUser;

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
     * Takes user to ModifyAppointment page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionUpdateAppt(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("ModifyAppointment.fxml","Update Appointment", updateApptButton);
    }

    /**
     * Takes user to ModifyCustomer page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("ModifyCustomer.fxml","Update Customer Information", updateCustomerButton);
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

    public static String getCurrentUser(){
        return currentUser;
    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
         !!! LINE OF CODE BELOW - FOR TESTING & DEVELOPMENT PURPOSES - REMOVE

         During development, I set the startup page to open to dashboard,
         so the login form wasn't able to set the current user.
         */
        UserDAO.setCurrentUser(new User(1, "test", "hidden"));

        welcomeText.setText("Welcome, " + UserDAO.getCurrentUser().getUsername() + "!");
    }
}
