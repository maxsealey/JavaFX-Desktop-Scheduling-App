package sealey.javafxdesktopschedulingapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class Customer {

    /**
     * Customer class constructor
     *
     * @param customerID id
     * @param customerName name
     * @param address home address
     * @param postalCode postal code
     * @param phone phone number
     * @param divisionID division id
     * */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, int divisionID, String location) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
        this.location = location;
    }

    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;
    private String location;
    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public static void setAppointmentList(ObservableList<Appointment> newList) {
        appointmentList = newList;
    }

    /**
     * @return customerID id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID id
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return customerName name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return address home address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address home address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return postalCode postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return phone phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return divisionID division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID division id
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
