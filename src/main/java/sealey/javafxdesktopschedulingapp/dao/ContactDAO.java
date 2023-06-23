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
 * Description:
 *
 * @author Max Sealey
 * */
public class ContactDAO {
    //static list of all contacts in the db
    private static ObservableList<Contact> contactList = FXCollections.observableArrayList();

    /**
     *
     * */
    public static ObservableList<Contact> getContactList() {
        return contactList;
    }

    /**
     *
     * */
    public static void setContactList(ObservableList<Contact> contactList) {
        ContactDAO.contactList = contactList;
    }

    /**
     *
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

    public static String convertIDtoName(int id){
        try{
            for(Contact c : contactList){
                if(c.getContactID() == id){
                    return c.getContactName();
                }
            }
        }catch(Exception e){
            System.out.println("Could not find contact.");
        }
        return null;
    }
}