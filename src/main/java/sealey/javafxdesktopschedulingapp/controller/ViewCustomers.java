package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sealey.javafxdesktopschedulingapp.dao.CustomerQuery;
import sealey.javafxdesktopschedulingapp.helpers.Alerts;
import sealey.javafxdesktopschedulingapp.helpers.FXML_Helpers;
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
        FXML_Helpers.setStage("ModifyCustomer.fxml","Update Customer Information", updateCustomerButton);
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
        boolean success = false;

        try {
            if(customerTable.getSelectionModel().isEmpty()){
                Alerts.message("Could not delete.", "Please select an item.", Alert.AlertType.ERROR);
                throw new NoSuchElementException();
            } else {
                // use retrieved customer ID (for the customer to be deleted) and
                // use it to loop through CustomerQuery customerList and determine whether

            int customerID = customerTable.getSelectionModel().getSelectedItem().getCustomerID();
            for(Customer c : CustomerQuery.getCustomerList()){
                if (c.getCustomerID() == customerID){

                }
            }



            }
        } catch(NoSuchElementException e) {
            System.out.println("no item selected error");
            return;
        }
    }

    /**
     *
     * @param url location used to resolve relative paths for the root object, or null
     * @param resourceBundle resources used to localize root object or null
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CustomerQuery.populateCustomerList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FXML_Helpers.setCustomerTable(CustomerQuery.getCustomerList(), customerTable, idCol, nameCol, addressCol, postalCol, phoneCol, divCol);
    }
}
