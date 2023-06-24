package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.dao.ContactDAO;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.dao.UserDAO;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class AppointmentSchedule implements Initializable
{
    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, Integer> aptIDCol;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Appointment, String> contactCol;

    @FXML
    private TableColumn<Appointment, Integer> custIDCol;

    @FXML
    private TableColumn<Appointment, String> descCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;

    @FXML
    private TableColumn<Appointment, String> locationCol;

    @FXML
    private Button newApptButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<Appointment, Integer> userIDCol;

    @FXML
    private RadioButton viewByMonthRadio;

    @FXML
    private RadioButton viewByWeekRadio;

    @FXML
    private RadioButton viewAllRadio;

    @FXML
    private ToggleGroup viewToggleGroup;

    /**
     * Returns user to dashboard
     *
     * @param event Back button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", backButton);
    }

    /**
     * Takes user to AddAppointment page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionNewAppointment(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("AddAppointment.fxml", "Add New Appointment", newApptButton);
    }

    /**
     * Takes user to ModifyAppointment page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {
        try {
            ModifyAppointment.setToUpdate(appointmentTable.getSelectionModel().getSelectedItem());
            FXML_Helpers.setStage("ModifyAppointment.fxml","Update Appointment", updateButton);
        } catch(Exception e){
            Alerts.message("Please select an item", "No item was selected. Please select an item you would like" +
                    " to update and try again.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onActionDelete(ActionEvent event){
        try {
            if(appointmentTable.getSelectionModel().isEmpty()){
                Alerts.message("Could not delete.", "Please select an item.", Alert.AlertType.ERROR);
                throw new NoSuchElementException();
            } else {
                if(Alerts.deleteConfirmation()){
                    Alerts.cancelledAppointment(appointmentTable.getSelectionModel().getSelectedItem());
                    AppointmentDAO.deleteAppointment(appointmentTable.getSelectionModel().getSelectedItem().getAppointmentID());
                    AppointmentDAO.populateAppointmentList();
                    return;
                }
            }
        } catch(NoSuchElementException e){
            System.out.println("no item selected");
        } catch (SQLException e) {
            System.out.println("Error repopulating table from db");
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onActionWeek(ActionEvent event) {

    }

    @FXML
    void onActionMonth(ActionEvent event) {

    }

    @FXML
    void onActionAll(ActionEvent event) {

    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AppointmentDAO.populateAppointmentList();
            ContactDAO.populateContactList();
            CustomerDAO.populateCustomerList();
            UserDAO.populateUserList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FXML_Helpers.setAppointmentTable(AppointmentDAO.getAppointmentList(), appointmentTable, aptIDCol, custIDCol,
                userIDCol, contactCol, titleCol, descCol, locationCol, typeCol, startCol, endCol);
    }
}