package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: This class contains static methods to interact with the database on contact data
 *
 * @author maxsealey Max Sealey
 * */
public class ContactDAO {
    /**
     * @return contactList gets list of contacts from database and returns
     * */
    public static ObservableList<Contact> getContactList() throws SQLException {
        ObservableList<Contact> list = FXCollections.observableArrayList();
        String sql = "SELECT Contact_ID, Contact_Name, Email FROM client_schedule.contacts";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next())
        {
            int contactID = results.getInt("Contact_ID");
            String contactName = results.getString("Contact_Name");
            String email = results.getString("Email");

            list.add(new Contact(contactID, contactName, email));
        }
        return list;
    }
}