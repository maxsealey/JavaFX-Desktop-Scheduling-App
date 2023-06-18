package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class CustomerQuery {

    // static list of all customers currently in the database
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /**
     * Returns customer list
     *
     * @return customerList list
     * */
    public static ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Sets new list - EXERCISE CAUTION
     *
     * @param newList resets entire list of customers
     * */
    private static void setCustomerList(ObservableList<Customer> newList){
        customerList = newList;
    }

    /**
     * Populates customer list from database
     * */
    public static void populateCustomerList() throws SQLException {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM client_schedule.customers";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet results = ps.executeQuery();

            customerList.clear();

            while(results.next())
            {
                int customerID = results.getInt("Customer_ID");
                String name = results.getString("Customer_Name");
                String address = results.getString("Address");
                String postal = results.getString("Postal_Code");
                String phone = results.getString("Phone");
                int divisionID = results.getInt("Division_ID");
                String location = getLocation(divisionID);

                customerList.add(new Customer(customerID, name, address, postal, phone, divisionID, location));
            }
    }

    /**
     * uses division_ID FK from customer table and retrieves division and country names
     *
     * @param division_id divisionID that points to customer FLD and Country names
     * @return location returns division name + country name concatenated
     * */
    private static String getLocation(int division_id) throws SQLException {
        String sql = "SELECT FD.Division, C.Country FROM client_schedule.customers AS CUS " +
        "RIGHT JOIN client_schedule.first_level_divisions AS FD ON CUS.Division_ID = FD.Division_ID " +
        "RIGHT JOIN client_schedule.countries AS C ON FD.Country_ID = C.Country_ID " +
                "WHERE FD.Division_ID = " + division_id;

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();
        String location = "";

        while(results.next()){
            // will always only contain one result unless duplicate primary key error (which would break the program)
            location = results.getString("Division") + ", " + results.getString("Country");
        }
        return location;
    }

    /**
     * Deletes customer from the database
     * */
    public static int deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = " + customerID;
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, customerID);

        return ps.executeUpdate();
    }
}
