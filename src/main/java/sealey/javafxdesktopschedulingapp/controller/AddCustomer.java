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
 * Description:
 *
 * @author Max Sealey
 * */
public class AddCustomer implements Initializable
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

    /**
     * Returns user to dashboard without saving
     *
     * @param event Cancel button event
     * @throws IOException IOException
     * */
    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
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
    private void onActionSave(ActionEvent event) throws IOException {
        try {
            if(Alerts.confirmSave()){
                try {
                    if(newCustomer()){
                        FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", saveButton);
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

    @FXML
    private void onActionCountry(ActionEvent event) throws SQLException {
        fldComboBox.getSelectionModel().clearSelection();
        FXML_Helpers.setFLDComboBox(fldComboBox, countryComboBox.getValue());
    }

    /**
     * Gets data from fields/boxes, creates new Customer, inserts into db
     * Called in onActionSave event handler
     * */
    private boolean newCustomer() throws SQLException {
        try {
            if(nameTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || postalCodeTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() || fldComboBox.getValue().isEmpty() || countryComboBox.getValue().isEmpty()){
                //Alerts.message("Something went wrong.", "All fields must be correctly filled out.", Alert.AlertType.ERROR);
                throw new Exception();
            }

            int id = Integer.parseInt(IDTextField.getText());
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneTextField.getText();

            int divisionID = LocationDAO.getDivisionID(fldComboBox.getValue());
            String location = fldComboBox.getValue() + ", " + countryComboBox.getValue();

            Customer newCustomer = new Customer(id, name, address, postalCode, phone, divisionID, location);

            System.out.println(CustomerDAO.insertCustomer(newCustomer));
        } catch(Exception e){
            return false;
        }
        return true;
    }


    /**
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

        FXML_Helpers.setCountryComboBox(countryComboBox);
        fldComboBox.setPromptText("Divisions");
        IDTextField.setText(String.valueOf(Misc_Helpers.getNextCustomerID()));
    }
}
