package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery {

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

                customerList.add(new Customer(customerID, name, address, postal, phone, divisionID));
            }
    }
}
