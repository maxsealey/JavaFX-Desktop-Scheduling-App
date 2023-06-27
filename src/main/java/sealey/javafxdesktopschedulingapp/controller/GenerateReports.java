package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.dao.ReportsDAO;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.helpers.Misc_Helpers;
import sealey.javafxdesktopschedulingapp.model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Description: This class is the controller for the page that generates the three reports.
 *
 * @author maxsealey Sealey
 * */
public class GenerateReports implements Initializable
{
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDCol;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> contactNameComboBox;

    @FXML
    private TableColumn<Appointment, Integer> customerIDCol;

    @FXML
    private ComboBox<String> customerNameComboBox;

    @FXML
    private TableColumn<Appointment, String> descCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;

    @FXML
    private TableView<Appointment> scheduleTable;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TextField totalAppointmentsByNameTextField;

    @FXML
    private TextField totalAppointmentsByTypeMonthTextField;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private ComboBox<String> monthComboBox2;

    /**
     * Sends user back to dashboard
     *
     * @param event back button
     * @throws IOException IOException
     * */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", backButton);
    }

    /**
     * When ComboBox containing lists of customers changes, refreshes table to filter by contact
     *
     * @param event ComboBox
     * @throws SQLException
     * */
    @FXML
    void onActionScheduleComboBox(ActionEvent event) throws SQLException {
        int contactID = Misc_Helpers.splitID(contactNameComboBox.getValue());
        FXML_Helpers.setAppointmentReportTable(Misc_Helpers.filteredAppointmentsForReport(contactID), scheduleTable, appointmentIDCol, titleCol,
                descCol, typeCol, startCol, endCol, customerIDCol);
    }

    /**
     * Gets and sets number of appointments of each type for in each month
     *
     * @param event ComboBox
     * @throws SQLException
     * */
    @FXML
    void onActionTypeComboBox(ActionEvent event) throws SQLException {
        try {
            int count = ReportsDAO.getTotalByTypeMonth(typeComboBox.getValue(), Misc_Helpers.splitID(monthComboBox.getValue()));
            totalAppointmentsByTypeMonthTextField.setText("Total: " + count);
        } catch(NullPointerException ignored){}
    }

    /**
     * Gets and sets number of appointments of each type for in each month
     *
     * @param event ComboBox
     * @throws SQLException
     * */
    @FXML
    void onActionMonthComboBox(ActionEvent event) throws SQLException {
        try {
            int count = ReportsDAO.getTotalByTypeMonth(typeComboBox.getValue(), Misc_Helpers.splitID(monthComboBox.getValue()));
            totalAppointmentsByTypeMonthTextField.setText("Total: " + count);
        } catch(NullPointerException ignored){}
    }

    /**
     * Gets and sets number of appointments customer has in each month
     *
     * @param event ComboBox
     * @throws SQLException
     * */
    @FXML
    void onActionCustomerNameComboBox(ActionEvent event) throws SQLException {
        try {
            int count = ReportsDAO.getTotalByCustomerMonth(Misc_Helpers.splitID(customerNameComboBox.getValue()), Misc_Helpers.splitID(monthComboBox2.getValue()));
            totalAppointmentsByNameTextField.setText("Total: " + count);
        } catch(NullPointerException ignored){}
    }

    /**
     * Gets and sets number of appointments customer has in each month
     *
     * @param event ComboBox
     * @throws SQLException
     * */
    @FXML
    void onActionMonth2ComboBox(ActionEvent event) throws SQLException {
        try {
            int count = ReportsDAO.getTotalByCustomerMonth(Misc_Helpers.splitID(customerNameComboBox.getValue()), Misc_Helpers.splitID(monthComboBox2.getValue()));
            totalAppointmentsByNameTextField.setText("Total: " + count);
        } catch(NullPointerException ignored){}
    }

    /**
     * Runs on initialization, sets ComboBox values
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXML_Helpers.setContactComboBox(contactNameComboBox);
            FXML_Helpers.setTypesComboBox(typeComboBox);
            FXML_Helpers.setCustomerComboBox(customerNameComboBox);
            FXML_Helpers.setMonthsComboBox(monthComboBox);
            FXML_Helpers.setMonthsComboBox(monthComboBox2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
