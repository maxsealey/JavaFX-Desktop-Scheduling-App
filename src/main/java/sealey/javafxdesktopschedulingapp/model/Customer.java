package sealey.javafxdesktopschedulingapp.model;

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
     * @param createDate date added to db
     * @param createdBy user who added customer to db
     * @param lastUpdate date last updated
     * @param lastUpdatedBy user who last updated customer info
     * @param divisionID division id
     * */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, String createDate,
                    String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

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

    /**
     * @return createDate date added to db
     * */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate date added to db
     * */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return createdBy user who added to db
     * */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy user who added to db
     * */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return lastUpdate Timestamp of last update
     * */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate Timestamp of last update
     * */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return lastUpdatedBy user who last updated
     * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy user who last updated
     * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
