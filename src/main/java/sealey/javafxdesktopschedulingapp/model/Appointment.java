package sealey.javafxdesktopschedulingapp.model;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class Appointment {

    /**
     * @param appointmentID appointment id
     * @param customerID customer id
     * @param userID user id
     * @param contactID contact id
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param startDateTime start time and date
     * @param endDateTime end time and date
     */
    public Appointment(int appointmentID, int customerID, int userID, int contactID, String title, String description, String location, String type,
                       String startDateTime, String endDateTime)
    {

        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    private int appointmentID;
    private int customerID;
    private int userID;
    private int contactID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String startDateTime;
    private String endDateTime;

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
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
     * @return userID id
     * */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID id
     * */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return contactID id
     * */
    public int getContactID() {
        return contactID;
    }

    /**
     * @param contactID id
     * */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @return title appointment title
     * */
    public String getTitle() {
        return title;
    }

    /**
     * @param title appointment title
     * */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return description appointment description
     * */
    public String getDescription() {
        return description;
    }

    /**
     * @param description appointment description
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return location appointment location
     * */
    public String getLocation() {
        return location;
    }

    /**
     * @param location appointment location
     * */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return type appointment type
     * */
    public String getType() {
        return type;
    }

    /**
     * @param type appointment type
     * */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return startDateTime start time and date
     * */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime start time and date
     * */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return endDateTime end time and date
     * */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime end time and date
     * */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
