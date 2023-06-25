package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.dao.LocationDAO;
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
 * Description: This class is the controller for the Modify Customer page
 *
 * @author maxsealey Sealey
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

    private static Customer toUpdate;

    /**
     * Sets the static Customer object to contain data that will be assigned to
     * controls, and eventually updated
     *
     * @param toUpdate sets toUpdate object
     * */
    public static void setToUpdate(Customer toUpdate) {
        ModifyCustomer.toUpdate = toUpdate;
    }

    /**
     * Returns user to customer page without saving
     *
     * @param event Cancel button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmCancel()){
                FXML_Helpers.setStage("ViewCustomers.fxml", "All Customers", cancelButton);
            }
        } catch(NoSuchElementException e) {
            System.out.println("cancel cancel");
        }
    }

    /**
     * Saves customer data, returns user to customer page
     *
     * @param event Save button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmSave()){
                try {
                    if(modifyCustomer()){
                        CustomerDAO.updateCustomer(toUpdate);
                        FXML_Helpers.setStage("ViewCustomers.fxml", "All Customers", saveButton);
                    } else {
                        throw new Exception();
                    }

                } catch(Exception e){
                    Alerts.message("Something went wrong.", "Please make sure all of the fields are correctly filled out. \n\n" +
                            "The address field can contain up to 100 characters, and the others can contain up to 50.", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e){
            System.out.println("cancel save");
        }
    }

    /**
     * When Country combo box changes, reverts division combo box
     *
     * @param event Country ComboBox
     * @throws SQLException
     * */
    @FXML
    private void onActionCountry(ActionEvent event) throws SQLException {
        fldComboBox.getSelectionModel().clearSelection();
        FXML_Helpers.setFLDComboBox(fldComboBox, countryComboBox.getValue());
    }

    /**
     * Runs on initialization - sets values into all controls
     *
     * @param c customer object containing old values
     * */
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
     * Updates customer in database or throws exception (if fields are empty).
     *
     * @return boolean true if update successful, false if not
     * @throws SQLException database insertion protection
     * */
    private boolean modifyCustomer() throws SQLException {
        try {
            if(nameTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || postalCodeTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() || fldComboBox.getValue().isEmpty() || countryComboBox.getValue().isEmpty()){
                throw new Exception();
            }

            int id = Integer.parseInt(IDTextField.getText());
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneTextField.getText();

            int divisionID = LocationDAO.getMatchDivisionID(fldComboBox.getValue());
            String location = fldComboBox.getValue() + ", " + countryComboBox.getValue();

            toUpdate = new Customer(id, name, address, postalCode, phone, divisionID, location);
        } catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * Runs on scene initialization, sets controls and local toUpdate object
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCustomer(toUpdate);
        try {
            FXML_Helpers.setFLDComboBox(fldComboBox, countryComboBox.getValue());
            FXML_Helpers.setCountryComboBox(countryComboBox);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
