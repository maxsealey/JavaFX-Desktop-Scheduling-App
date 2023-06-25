package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * Description: This class is the controller for the page to view all customers.
 *
 * @author maxsealey Sealey
 * */
public class ViewCustomers implements Initializable
{
    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> addressCol;

    @FXML
    private TableColumn<Customer, String> divCol;

    @FXML
    private TableColumn<Customer, Integer> idCol;

    @FXML
    private TableColumn<Customer, String> nameCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, String> postalCol;

    @FXML
    private Button newAppointmentButton;

    @FXML
    private Button newCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;



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
        FXML_Helpers.setStage("AddAppointment.fxml", "Add New Appointment", newAppointmentButton);
    }

    /**
     * Takes user to ModifyAppointment page
     *
     * @param event Change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        try {
            ModifyCustomer.setToUpdate(customerTable.getSelectionModel().getSelectedItem());
            FXML_Helpers.setStage("ModifyCustomer.fxml","Update Customer", updateCustomerButton);
        } catch(Exception e)
        {
            System.out.println("error updating customer");
            Alerts.message("Please select an item", "No item was selected. Please select an item you would like" +
                    " to update and try again.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Takes user to New Customer page
     *
     * @param event change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionNewCustomer(ActionEvent event) throws IOException {
        FXML_Helpers.setStage("AddCustomer.fxml","Add New Customer", newCustomerButton);
    }

    /**
     * Deletes customer if no associated appointments attached
     *
     * @param event change scene button event
     * @throws IOException IOException
     * */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws IOException {
        try {
            if(customerTable.getSelectionModel().isEmpty()){
                Alerts.message("Could not delete.", "Please select an item.", Alert.AlertType.ERROR);
                throw new NoSuchElementException();
            } else {
                // Customer cannot be deleted while they have an existing appointment
                boolean emptyAptList = CustomerDAO.appointmentListIsEmpty(customerTable.getSelectionModel().getSelectedItem().getCustomerID());

                // Delete customer from database and repopulate
                if(emptyAptList && Alerts.deleteConfirmation()){
                    CustomerDAO.deleteCustomer(customerTable.getSelectionModel().getSelectedItem().getCustomerID());

                    Alerts.message("Success", "Customer successfully removed from" +
                            " the database.", Alert.AlertType.INFORMATION);
                    FXML_Helpers.setCustomerTable(CustomerDAO.getCustomerList(), customerTable, idCol, nameCol, addressCol, postalCol, phoneCol, divCol);
                } else if (!emptyAptList){
                    Alerts.message("Customer has existing appointment(s)",
                            "We were unable to delete the customer because they have an existing" +
                                    " appointment. Please remove the appointment(s) and try again.", Alert.AlertType.ERROR);
                }
            }
        } catch(NoSuchElementException e) {
            System.out.println("no item selected");
            return;
        } catch (SQLException e) {
            System.out.println("Error repopulating table from db");
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs on initialization, sets customer table
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXML_Helpers.setCustomerTable(CustomerDAO.getCustomerList(), customerTable, idCol, nameCol, addressCol, postalCol, phoneCol, divCol);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
