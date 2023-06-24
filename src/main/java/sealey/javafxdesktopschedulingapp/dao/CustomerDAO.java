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
 * Description:
 *
 * @author Max Sealey
 * */
public class CustomerDAO {

    // static list of all customers currently in the database
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /**
     * Returns customer list
     *
     * @return customerList list
     */
    public static ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Populates customer list from database
     */
    public static void populateCustomerList() throws SQLException {
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM client_schedule.customers";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        customerList.clear();

        while (results.next()) {
            int customerID = results.getInt("Customer_ID");
            String name = results.getString("Customer_Name");
            String address = results.getString("Address");
            String postal = results.getString("Postal_Code");
            String phone = results.getString("Phone");
            int divisionID = results.getInt("Division_ID");
            String location = LocationDAO.getLocation(divisionID);

            customerList.add(new Customer(customerID, name, address, postal, phone, divisionID, location));
        }
    }

    /**
     * Insert a customer object into the database
     *
     * @param newCustomer new customer object
     * @return int 1 or 0
     */
    public static int insertCustomer(Customer newCustomer) throws SQLException {
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

        return ps.executeUpdate();
    }

    /**
     * Deletes customer from the database
     *
     * @param customerID id of customer to delete
     */
    public static void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, customerID);

        ps.executeUpdate();

    }

    /**
     *
     */
    public static int updateCustomer(Customer newCustomer) throws SQLException {
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

        return ps.executeUpdate();
    }

    public static void populateCustomerAppointmentList() throws SQLException {
        AppointmentDAO.populateAppointmentList();
        for (Appointment a : AppointmentDAO.getAppointmentList()) {
            for (Customer c : CustomerDAO.getCustomerList()) {
                if (c.getCustomerID() == a.getCustomerID()) {
                    c.addAppointment(a);
                }
            }
        }
    }
}
