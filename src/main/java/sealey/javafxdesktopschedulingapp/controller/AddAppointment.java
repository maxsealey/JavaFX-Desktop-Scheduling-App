package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.dao.ContactDAO;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.dao.LocationDAO;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
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


    /**
     * Returns user to dashboard without saving
     *
     * @param event Cancel button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", cancelButton);
    }

    /**
     * Saves customer data, returns user to dashboard
     *
     * @param event Save button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmSave()){
                try {
                    if(newAppointment()){
                        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", saveButton);
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

    private boolean newAppointment() throws SQLException {
        try {
            if(titleTextField.getText().isEmpty() || descTextArea.getText().isEmpty() || locationTextField.getText().isEmpty()
                    || typeTextField.getText().isEmpty() || contactComboBox.getValue().isEmpty() || customerComboBox.getValue().isEmpty()
                    || userComboBox.getValue().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue() == null
                    || startTimeCombo.getValue() == null || endTimeCombo.getValue() == null){
                throw new Exception();
            }

            //take data and insert into db


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

            FXML_Helpers.setUserComboBox(userComboBox);
            FXML_Helpers.setCustomerComboBox(customerComboBox);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FXML_Helpers.setContactComboBox(contactComboBox);
        apptIDTextField.setText(String.valueOf(AppointmentDAO.getNextID()));

    }
}
