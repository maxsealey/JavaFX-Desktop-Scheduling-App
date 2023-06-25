package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.Contact;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Description: This class contains static methods to interact with the database on contact data
 *
 * @author maxsealey Max Sealey
 * */
public class ContactDAO {
    //static list of all contacts in the db
    private static ObservableList<Contact> contactList = FXCollections.observableArrayList();

    /**
     * @return contactList getter
     * */
    public static ObservableList<Contact> getContactList() {
        return contactList;
    }

    /**
     * @param contactList setter (unused)
     * */
    public static void setContactList(ObservableList<Contact> contactList) {
        ContactDAO.contactList = contactList;
    }

    /**
     * Populates local static contactList with data retrieved from the database
     *
     * @throws SQLException sql protection
     * */
    public static void populateContactList() throws SQLException {
        String sql = "SELECT Contact_ID, Contact_Name, Email FROM client_schedule.contacts";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        contactList.clear();

        while(results.next())
        {
            int contactID = results.getInt("Contact_ID");
            String contactName = results.getString("Contact_Name");
            String email = results.getString("Email");

            contactList.add(new Contact(contactID, contactName, email));
        }
    }
}