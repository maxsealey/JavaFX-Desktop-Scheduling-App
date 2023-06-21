package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.helpers.Misc_Helpers;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class ModifyCustomer implements Initializable
{
    @FXML
    private TextField IDTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private ComboBox<String> fldComboBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Label headerLabel;

    private static Customer toUpdate;

    public static Customer getToUpdate() {
        return toUpdate;
    }

    public static void setToUpdate(Customer toUpdate) {
        ModifyCustomer.toUpdate = toUpdate;
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
                FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", cancelButton);
            }
        } catch(NoSuchElementException e) {
            System.out.println("cancel cancel");
        }
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
    private void onActionCountry(ActionEvent event) throws SQLException {
        fldComboBox.getSelectionModel().clearSelection();
        FXML_Helpers.setFLDComboBox(fldComboBox, countryComboBox.getValue());
    }

    private void setCustomer(Customer c) {
        IDTextField.setText((String.valueOf(c.getCustomerID())));
        nameTextField.setText((String.valueOf(c.getCustomerName())));
        phoneTextField.setText((String.valueOf(c.getPhone())));
        addressTextField.setText((String.valueOf(c.getAddress())));
        postalCodeTextField.setText((String.valueOf(c.getPostalCode())));

        fldComboBox.setValue(Misc_Helpers.splitLocation(c.getLocation()).get(0));
        countryComboBox.setValue(Misc_Helpers.splitLocation(c.getLocation()).get(1));
    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CustomerDAO.populateCustomerList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCustomer(toUpdate);
        FXML_Helpers.setCountryComboBox(countryComboBox);
        fldComboBox.setPromptText("Divisions");
    }
}
