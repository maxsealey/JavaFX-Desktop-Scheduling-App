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
import sealey.javafxdesktopschedulingapp.model.Contact;
import sealey.javafxdesktopschedulingapp.model.Customer;
import sealey.javafxdesktopschedulingapp.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class ModifyAppointment implements Initializable
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
    private Label headerLabel;

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

    private static Appointment toUpdate;

    public static Appointment getToUpdate() {
        return toUpdate;
    }

    public static void setToUpdate(Appointment toUpdate) {
        ModifyAppointment.toUpdate = toUpdate;
    }


    /**
     * Returns user to dashboard without saving
     *
     * @param event Cancel button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmCancel()){
                FXML_Helpers.setStage("AppointmentSchedule.fxml", "Appointment Schedule", cancelButton);
            }
        } catch(NoSuchElementException e) {
            System.out.println("cancel cancel");
        }
    }

    /**
     * Saves customer data, returns user to dashboard
     *
     * LAMBDA EXPRESSION USAGE: Used a lambda to remove an appointment from the appointment list if the id is the same
     * as the one that I'm updating. This allows my overlap checker to work for ModifyAppointment.
     *
     * @param event Save button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmSave()){
                try {
                    try {
                        if(!Time_Helpers.timeValidityCheck(LocalDateTime.of(startDatePicker.getValue(), startTimeCombo.getValue()),
                                LocalDateTime.of(endDatePicker.getValue(), endTimeCombo.getValue()))){
                            throw new Exception();
                        }
                    } catch(Exception e){
                        Alerts.businessHoursAlert();
                        return;
                    }

                    try {
                        LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTimeCombo.getValue());
                        LocalDateTime endDateTime = LocalDateTime.of(endDatePicker.getValue(), endTimeCombo.getValue());

                        AppointmentDAO.getAppointmentList().removeIf(a -> a.getAppointmentID() == Integer.parseInt(apptIDTextField.getText()));

                        if(!Time_Helpers.checkCustomerOverlap(Misc_Helpers.splitID(customerComboBox.getValue()), startDateTime, endDateTime))
                        {
                            throw new Exception();
                        }
                    } catch(Exception e){
                        Alerts.overlappingAppointmentsAlert();
                        return;
                    }

                    if(modifyAppointment()){
                        AppointmentDAO.updateAppointment(toUpdate);
                        FXML_Helpers.setStage("AppointmentSchedule.fxml", "Appointment Schedule", saveButton);
                        return;
                    } else {
                        throw new Exception();
                    }

                } catch(Exception e){
                    Alerts.message("Something went wrong.", "Please make sure all of the fields are correctly filled out. \n\n" +
                            "All of the fields can contain no more than 50 characters.", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e){
            System.out.println("cancel save");
        }
    }

    @FXML
    void onActionStartDate(ActionEvent event) {

    }

    @FXML
    void onActionStartTime(ActionEvent event) {

    }

    @FXML
    void onActionEndDate(ActionEvent event) {

    }

    @FXML
    void onActionEndTime(ActionEvent event) {

    }

    @FXML
    void onActionContactCombo(ActionEvent event) {

    }

    @FXML
    void onActionCustomerCombo(ActionEvent event) {

    }

    @FXML
    void onActionUserCombo(ActionEvent event) {

    }

    private void setAppointment(Appointment a) {
        apptIDTextField.setText(String.valueOf(a.getAppointmentID()));
        titleTextField.setText(a.getTitle());
        descTextArea.setText(a.getDescription());
        locationTextField.setText(a.getLocation());
        typeTextField.setText(a.getType());

        String contactName = null, customerName = null, userName = null;
        for (Contact c : ContactDAO.getContactList()) {
            if (c.getContactID() == a.getContactID()) {
                contactName = c.getContactName();
            }
        }

        for (Customer c : CustomerDAO.getCustomerList()) {
            if (c.getCustomerID() == a.getCustomerID()) {
                customerName = c.getCustomerName();
            }
        }

        for (User u : UserDAO.getUserList()) {
            if (u.getUserID() == a.getUserID()) {
                userName = u.getUsername();
            }
        }

        contactComboBox.setValue(a.getContactID() + " " + contactName);
        customerComboBox.setValue(a.getCustomerID() + " " + customerName);
        userComboBox.setValue(a.getUserID() + " " + userName);

        startDatePicker.setValue(a.getStartDateTime().toLocalDate());
        endDatePicker.setValue(a.getEndDateTime().toLocalDate());
        startTimeCombo.setValue(a.getStartDateTime().toLocalTime());
        endTimeCombo.setValue(a.getEndDateTime().toLocalTime());
    }

    private boolean modifyAppointment() throws SQLException {
        try {
            if(titleTextField.getText().isEmpty() || descTextArea.getText().isEmpty() || locationTextField.getText().isEmpty()
                    || typeTextField.getText().isEmpty() || contactComboBox.getValue().isEmpty() || customerComboBox.getValue().isEmpty()
                    || userComboBox.getValue().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue() == null
                    || startTimeCombo.getValue() == null || endTimeCombo.getValue() == null){
                throw new Exception();
            }

            int appointmentID = Integer.parseInt(apptIDTextField.getText());

            int customerID = Misc_Helpers.splitID(customerComboBox.getValue());
            int userID = Misc_Helpers.splitID(userComboBox.getValue());
            int contactID = Misc_Helpers.splitID(contactComboBox.getValue());

            String title = titleTextField.getText();
            String desc = descTextArea.getText();
            String loc = locationTextField.getText();
            String type = typeTextField.getText();

            LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTimeCombo.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(endDatePicker.getValue(), endTimeCombo.getValue());

            ZonedDateTime utcStartZDT = Time_Helpers.localToUTC(startDateTime);
            ZonedDateTime utcEndZDT = Time_Helpers.localToUTC(endDateTime);


            toUpdate = new Appointment(appointmentID, customerID, userID, contactID, title, desc,
                    loc, type, utcStartZDT.toLocalDateTime(), utcEndZDT.toLocalDateTime());
        } catch(Exception e){
            return false;
        }
        return true;
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
            FXML_Helpers.setCustomerComboBox(customerComboBox);
            FXML_Helpers.setUserComboBox(userComboBox);
            FXML_Helpers.setContactComboBox(contactComboBox);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Time_Helpers.setTimesInComboBoxes(startTimeCombo, "Start", 1);
        Time_Helpers.setTimesInComboBoxes(endTimeCombo, "End", 1);

        setAppointment(toUpdate);
        timeZoneLabel.setText("Time Zone: " + ZoneId.systemDefault());
    }
}