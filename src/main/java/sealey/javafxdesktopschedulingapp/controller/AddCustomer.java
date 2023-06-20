package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.dao.LocationDAO;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.model.Country;
import sealey.javafxdesktopschedulingapp.model.Customer;
import sealey.javafxdesktopschedulingapp.model.FirstLevDivision;

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
    @FXML
    private Label headerLabel;

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
        try {
            if(Alerts.confirmSave()){
                try {
                    newCustomer();
                    FXML_Helpers.setStage("Dashboard.fxml", "Employee Dashboard", saveButton);
                } catch(Exception ignored){}
            }
        } catch (NoSuchElementException e){
            System.out.println("cancel save");
        }
    }

    @FXML
    void onActionCountry(ActionEvent event) {

    }

    @FXML
    void onActionFLD(ActionEvent event) {

    }

    /**
     * Gets data from fields/boxes, creates new Customer, inserts into db
     * Called in onActionSave event handler
     * */
    private void newCustomer() throws SQLException {
       int id = Integer.parseInt(IDTextField.getText());
       String name = nameTextField.getText();
       String address = addressTextField.getText();
       String postalCode = postalCodeTextField.getText();
       String phone = phoneTextField.getText();

       int divisionID = LocationDAO.getDivisionID(fldComboBox.getValue());
       String location = fldComboBox.getValue() + ", " + countryComboBox.getValue();

       Customer newCustomer = new Customer(id, name, address, postalCode, phone, divisionID, location);

       System.out.println(CustomerDAO.insertCustomer(newCustomer));
    }



    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXML_Helpers.setFLDComboBox(fldComboBox);
        FXML_Helpers.setCountryComboBox(countryComboBox);

        try {
            CustomerDAO.populateCustomerList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        IDTextField.setText(String.valueOf(CustomerDAO.getNextID()));
    }
}
