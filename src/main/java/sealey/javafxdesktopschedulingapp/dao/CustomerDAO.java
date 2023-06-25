package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.Appointment;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Description: This class contains static methods to interact with the database on customer data
 *
 * @author maxsealey Sealey
 * */
public class CustomerDAO {
    /**
     * @return customerList gets list of customers from database and returns
     */
    public static ObservableList<Customer> getCustomerList() throws SQLException {
        ObservableList<Customer> list = FXCollections.observableArrayList();

        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM client_schedule.customers";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while (results.next()) {
            int customerID = results.getInt("Customer_ID");
            String name = results.getString("Customer_Name");
            String address = results.getString("Address");
            String postal = results.getString("Postal_Code");
            String phone = results.getString("Phone");
            int divisionID = results.getInt("Division_ID");
            String location = LocationDAO.getLocation(divisionID);

            list.add(new Customer(customerID, name, address, postal, phone, divisionID, location));
        }
        return list;
    }

    /**
     * Insert a customer object into the database
     *
     * @param newCustomer new customer object
     * @throws SQLException
     */
    public static void insertCustomer(Customer newCustomer) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID, " +
                "Create_Date, Created_By, Last_Update, Last_Updated_By) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, newCustomer.getCustomerID());
        ps.setString(2, newCustomer.getCustomerName());
        ps.setString(3, newCustomer.getAddress());
        ps.setString(4, newCustomer.getPostalCode());
        ps.setString(5, newCustomer.getPhone());
        ps.setInt(6, newCustomer.getDivisionID());
        ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(8, UserDAO.getCurrentUser().getUsername());
        ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(10, UserDAO.getCurrentUser().getUsername());

        ps.executeUpdate();
    }

    /**
     * Deletes customer from the database
     *
     * @param customerID id of customer to delete
     * @throws SQLException
     */
    public static void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, customerID);

        ps.executeUpdate();

    }

    /**
     * Updates customer in the database
     *
     * @param newCustomer modified customer object
     * @throws SQLException sql protection
     */
    public static void updateCustomer(Customer newCustomer) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?, " +
                "Last_Update = ?, Last_Updated_By = ? WHERE Customer_ID = ?";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, newCustomer.getCustomerName());
        ps.setString(2, newCustomer.getAddress());
        ps.setString(3, newCustomer.getPostalCode());
        ps.setString(4, newCustomer.getPhone());
        ps.setInt(5, newCustomer.getDivisionID());
        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(7, UserDAO.getCurrentUser().getUsername());
        ps.setInt(8, newCustomer.getCustomerID());

        ps.executeUpdate();
    }

    /**
     * Returns true if customer has no associated appointments, false if they do
     *
     * @param customerID id of customer to check
     * @return boolean
     * @throws SQLException
     * */
    public static boolean appointmentListIsEmpty(int customerID) throws SQLException {
        for(Appointment a : AppointmentDAO.getAppointmentList()){
            if(a.getCustomerID() == customerID){
                return false;
            }
        }
        return true;
    }
}
