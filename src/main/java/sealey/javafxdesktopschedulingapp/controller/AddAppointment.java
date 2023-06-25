package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sealey.javafxdesktopschedulingapp.dao.*;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.helpers.Misc_Helpers;
import sealey.javafxdesktopschedulingapp.helpers.Time_Helpers;
import sealey.javafxdesktopschedulingapp.model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

/**
 * Description: This class is the controller for the page for adding appointments (AddAppointment.fxml)
 *
 * @author maxsealey Sealey
 * */
public class AddAppointment implements Initializable
{
    @FXML
    private TextField apptIDTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea descTextArea;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<LocalTime> endTimeCombo;

    @FXML
    private TextField locationTextField;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<LocalTime> startTimeCombo;

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private ComboBox<String> customerComboBox;
    @FXML
    private ComboBox<String> userComboBox;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private Label timeZoneLabel;


    /**
     * Returns user to the schedule screen without saving
     *
     * @param event Cancel button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("AppointmentSchedule.fxml", "Appointment Schedule", cancelButton);
    }

    /**
     * Saves customer data, returns user to schedule page. Also calls the methods that check that appointment times
     * for each customer do not overlap, and that they are within business hours (in EST).
     *
     * @param event Save button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmSave()){
                try {
                    try { // checks whether time is within business hours
                        if(!Time_Helpers.timeValidityCheck(LocalDateTime.of(startDatePicker.getValue(), startTimeCombo.getValue()),
                                LocalDateTime.of(endDatePicker.getValue(), endTimeCombo.getValue()))){
                            throw new Exception();
                        }
                    } catch(Exception e){
                        Alerts.businessHoursAlert();
                        return;
                    }

                    try { // checks whether the appointment would overlap with a customer's other appointments
                        LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTimeCombo.getValue());
                        LocalDateTime endDateTime = LocalDateTime.of(endDatePicker.getValue(), endTimeCombo.getValue());

                        if(!Time_Helpers.checkCustomerOverlap(AppointmentDAO.getAppointmentList(), Misc_Helpers.splitID(customerComboBox.getValue()), startDateTime, endDateTime))
                        {
                            throw new Exception();
                        }
                    } catch(Exception e){
                        Alerts.overlappingAppointmentsAlert();
                        return;
                    }

                    // if inserting a new appointment is successful, sends user to appointment schedule
                    if(newAppointment()){
                        FXML_Helpers.setStage("AppointmentSchedule.fxml", "Appointment Schedule", saveButton);
                    } else {
                        throw new Exception();
                    }
                } catch(Exception e){
                    Alerts.message("Something went wrong.", "Please make sure all of the fields are correctly filled out.", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e){
            System.out.println("cancel save");
        }
    }

    /**
     * Inserts appointment into database or throws exception (if fields are empty).
     *
     * @return boolean true if insertion successful, false if not
     * @throws SQLException database insertion protection
     * */
    private boolean newAppointment() throws SQLException {
        try {
            if(titleTextField.getText().isEmpty() || descTextArea.getText().isEmpty() || locationTextField.getText().isEmpty()
                    || typeTextField.getText().isEmpty() || contactComboBox.getValue().isEmpty() || customerComboBox.getValue().isEmpty()
                    || userComboBox.getValue().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue() == null
                    || startTimeCombo.getValue() == null || endTimeCombo.getValue() == null){
                throw new Exception(); // checks for empty control values
            }

            int appointmentID = Integer.parseInt(apptIDTextField.getText());
            int customerID = Misc_Helpers.splitID(customerComboBox.getValue());
            int userID = Misc_Helpers.splitID(userComboBox.getValue());
            int contactID = Misc_Helpers.splitID(contactComboBox.getValue());

            String title = titleTextField.getText();
            String desc = descTextArea.getText();
            String loc = locationTextField.getText();
            String type = typeTextField.getText();

            // Gets the LDTs in Local Time
            LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTimeCombo.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(endDatePicker.getValue(), endTimeCombo.getValue());

            // Converts LDTs to UTC
            ZonedDateTime utcStartZDT = Time_Helpers.localToUTC(startDateTime);
            ZonedDateTime utcEndZDT = Time_Helpers.localToUTC(endDateTime);

            try {
                Appointment newAppointment = new Appointment(appointmentID, customerID, userID, contactID, title,
                        desc, loc, type, utcStartZDT.toLocalDateTime(), utcEndZDT.toLocalDateTime());
                AppointmentDAO.insertAppointment(newAppointment);
            } catch(Exception ignored){}
        } catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * Runs on scene initialization, sets all ComboBox lists and the Time Zone label
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXML_Helpers.setCustomerComboBox(customerComboBox);
            FXML_Helpers.setUserComboBox(userComboBox);
            FXML_Helpers.setContactComboBox(contactComboBox);

            apptIDTextField.setText(String.valueOf(Misc_Helpers.getNextAppointmentID()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Time_Helpers.setTimesInComboBoxes(startTimeCombo, "Start", 0);
        Time_Helpers.setTimesInComboBoxes(endTimeCombo, "End", 0);

        timeZoneLabel.setText("Time Zone: " + ZoneId.systemDefault());
    }
}
