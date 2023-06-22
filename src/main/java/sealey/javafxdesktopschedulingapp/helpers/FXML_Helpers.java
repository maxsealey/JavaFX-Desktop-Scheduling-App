package sealey.javafxdesktopschedulingapp.helpers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sealey.javafxdesktopschedulingapp.Main;
import sealey.javafxdesktopschedulingapp.controller.ModifyCustomer;
import sealey.javafxdesktopschedulingapp.dao.ContactDAO;
import sealey.javafxdesktopschedulingapp.dao.LocationDAO;
import sealey.javafxdesktopschedulingapp.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class FXML_Helpers {

    /**
     * Helper function used globally (except for initial main load) to switch to a different scene
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
     * Sets cells in table of customers
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
     * Sets division names in combo box
     *
     * @param divisions ComboBox list of strings
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
     * Sets country names in combo box
     *
     * @param countries ComboBox list of strings
     * */
    public static void setCountryComboBox(ComboBox<String> countries){
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        for(Country c : LocationDAO.getCountryList())
        {
            countryNames.add(c.getCountry());
        }

        countries.setItems(countryNames);
        countries.setVisibleRowCount(3);
        countries.setPromptText("Countries");
    }

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

        contactName.setCellValueFactory(contact-> new SimpleStringProperty(ContactDAO.convertIDtoName(contact.getValue().getContactID())));
    }
}





