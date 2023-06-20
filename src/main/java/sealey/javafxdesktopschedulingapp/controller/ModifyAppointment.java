package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;

import java.io.IOException;
import java.net.URL;
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
    private TextField cusIDTextField;
    @FXML
    private TextArea descTextArea;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<?> endTimeCombo;
    @FXML
    private Label headerLabel;
    @FXML
    private TextField locationTextField;
    @FXML
    private Button saveButton;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private ComboBox<?> startTimeCombo;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField userIDTextField;

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
        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", saveButton);
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

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}