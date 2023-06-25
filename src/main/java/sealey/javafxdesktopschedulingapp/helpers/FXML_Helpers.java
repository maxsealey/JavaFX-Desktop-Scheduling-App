package sealey.javafxdesktopschedulingapp.helpers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sealey.javafxdesktopschedulingapp.Main;
import sealey.javafxdesktopschedulingapp.dao.*;
import sealey.javafxdesktopschedulingapp.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Description: This class contains helper functions to increase amount of reusable code
 *
 * @author maxsealey Sealey
 * */
public class FXML_Helpers {

    /**
     * Helper function used globally to switch to a different scene. Pass in data specific to each
     * stage of the program. Useful as this is a common action
     *
     * @param fxmlFile fxml file containing scene to be switched to
     * @param windowTitle title of the scene
     * @param node the interactable that contains the event listener
     * @throws IOException IOException
     * */
    public static void setStage(String fxmlFile, String windowTitle, Node node) throws IOException {
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Object root = fxmlLoader.load();
        Scene scene = new Scene((Parent) root);

        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.show();
    }

    /**
     * Sets cells in table of customers (ViewCustomer)
     *
     * @param customers
     * @param table
     * @param idCol
     * @param nameCol
     * @param addressCol
     * @param phoneCol
     * @param postalCol
     * @param divCol
     * */
    public static void setCustomerTable(ObservableList<Customer> customers, TableView<Customer> table,
                                        TableColumn<Customer, Integer> idCol, TableColumn<Customer, String> nameCol,
                                        TableColumn<Customer, String> addressCol, TableColumn<Customer, String> postalCol,
                                        TableColumn<Customer, String> phoneCol, TableColumn<Customer, String> divCol)
    {
        table.setItems(customers);

        idCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divCol.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    /**
     * Sets cells in table of appointments.
     * -------------------------------------------------
     * LAMBDA EXPRESSION USAGE #1:
     * -------------------------------------------------
     * Used a lambda expression in this method to take the value in the
     * contact ComboBox (an int, representing the contact ID), and convert it into
     * the contact name. This made it much easier to populate the Customer cells and
     * improved readability.
     *
     * @param appointmentList the list of appointments to be put into cells
     * @param appointmentTable the table to be filled out
     * @param appointmentID unique id assigned to each appointment
     * @param customerID unique id assigned to each customer
     * @param userID unique id assigned to each user
     * @param contactName name of each contact
     * @param title the title of the appointment
     * @param description the appointment description
     * @param location the location of the appointment
     * @param type the type of appointment
     * @param start the start date and time of the apppointment
     * @param end the end date and time of the appointment
     * */
    public static void setAppointmentTable(ObservableList<Appointment> appointmentList, TableView<Appointment> appointmentTable,
                                           TableColumn<Appointment, Integer> appointmentID, TableColumn<Appointment, Integer> customerID,
                                           TableColumn<Appointment, Integer> userID, TableColumn<Appointment, String> contactName,
                                           TableColumn<Appointment, String> title, TableColumn<Appointment, String> description,
                                           TableColumn<Appointment, String> location, TableColumn<Appointment, String> type,
                                           TableColumn<Appointment, LocalDateTime> start, TableColumn<Appointment, LocalDateTime> end)
    {
        appointmentTable.setItems(appointmentList);

        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));

        contactName.setCellValueFactory(contact-> new SimpleStringProperty(Misc_Helpers.convertContactIDtoName(contact.getValue().getContactID())));
    }

    /**
     * Sets appointment table on GenerateReport page
     *
     * @param appointments list of filtered appointments
     * @param appointmentTable TableView
     * @param appointmentIDCol appointment id column
     * @param titleCol title column
     * @param descCol description column
     * @param typeCol type column
     * @param startCol start time and date column
     * @param endCol end time and date column
     * @param customerIDCol customer id column
     * */
    public static void setAppointmentReportTable(ObservableList<Appointment> appointments, TableView<Appointment> appointmentTable,
                                                 TableColumn<Appointment, Integer> appointmentIDCol, TableColumn<Appointment, String> titleCol,
                                                 TableColumn<Appointment, String> descCol, TableColumn<Appointment, String> typeCol,
                                                 TableColumn<Appointment, LocalDateTime> startCol, TableColumn<Appointment, LocalDateTime> endCol,
                                                 TableColumn<Appointment, Integer> customerIDCol)
    {
        appointmentTable.setItems(appointments);

        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }

    /**
     * Sets division names in combo box (AddCustomer and ModifyCustomer). If a country is selected,
     * creates and returns a temporary list of FLDs that have the same Country ID. Then sets in FLD ComboBox
     *
     * @param divisions ComboBox list of strings
     * @param selectedCountry country to use to filter down to first-level divisions
     * @throws SQLException sql protection
     * */
    public static void setFLDComboBox(ComboBox<String> divisions, String selectedCountry) throws SQLException {
        ObservableList<String> divNames = FXCollections.observableArrayList();

        for(FirstLevDivision f : LocationDAO.getDivisionList()){
            if(f.getCountryID() == LocationDAO.getMatchCountryID(selectedCountry))
            {
                divNames.add(f.getDivisionName());
            }
        }

        divisions.setItems(divNames);
        divisions.setVisibleRowCount(8);
    }

    /**
     * Sets country names in ComboBox (AddCustomer and ModifyCustomer). Gets all countries.
     *
     * @param countries ComboBox list of strings
     * @throws SQLException
     * */
    public static void setCountryComboBox(ComboBox<String> countries) throws SQLException {
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        for(Country c : LocationDAO.getCountryList())
        {
            countryNames.add(c.getCountry());
        }

        countries.setItems(countryNames);
        countries.setVisibleRowCount(3);
        countries.setPromptText("Countries");
    }

    /**
     * Sets contact ID and contact name into ComboBox containing list of contacts (AddAppointment, ModifyAppointment, GenerateReports)
     *
     * @param contacts the ComboBox control
     * @throws SQLException sql protection
     * */
    public static void setContactComboBox(ComboBox<String> contacts) throws SQLException {
        ObservableList<String> contactNames = FXCollections.observableArrayList();

        for(Contact c : ContactDAO.getContactList()){
            contactNames.add(c.getContactID() + " " + c.getContactName());
        }

        contacts.setItems(contactNames);
        contacts.setVisibleRowCount(5);
        contacts.setPromptText("Contacts");
    }

    /**
     * Sets customer ID and name into ComboBox containing list of customers (AddAppointment, ModifyAppointment, GenerateReports)
     *
     * @param customers the ComboBox control
     * @throws SQLException sql protection
     * */
    public static void setCustomerComboBox(ComboBox<String> customers) throws SQLException {
        ObservableList<String> customerNames = FXCollections.observableArrayList();

        for(Customer c : CustomerDAO.getCustomerList()){
            customerNames.add(c.getCustomerID() + " " + c.getCustomerName());
        }

        customers.setItems(customerNames);
        customers.setVisibleRowCount(5);
        customers.setPromptText("Customers");
    }

    /**
     * Sets user ID and username into ComboBox containing list of users (AddAppointment, ModifyAppointment)
     *
     * @param users the ComboBox control
     * @throws SQLException sql protection
     * */
    public static void setUserComboBox(ComboBox<String> users) throws SQLException {
        ObservableList<String> usernames = FXCollections.observableArrayList();

        for(User u : UserDAO.getUserList()){
            usernames.add(u.getUserID() + " " + u.getUsername());
        }

        users.setItems(usernames);
        users.setVisibleRowCount(5);
        users.setPromptText("Users");
    }

    /**
     * Sets list of unique type options into ComboBox (GenerateReports)
     *
     * @param types the ComboBox control
     * @throws SQLException
     * */
    public static void setTypesComboBox(ComboBox<String> types) throws SQLException {
        types.setItems(ReportsDAO.getTypeList());
        types.setVisibleRowCount(5);
    }

    /**
     * Sets the list of month options in the ComboBox on the Generate Reports page
     *
     * @param months ComboBox control
     * */
    public static void setMonthsComboBox(ComboBox<String> months){
        months.setItems(Misc_Helpers.listOfMonths());
    }
}




