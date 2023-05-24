package sealey.javafxdesktopschedulingapp.model;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class Contact {

    /**
     * Contact class constructor
     *
     * @param contactID id
     * @param contactName name
     * @param email email address
     * */
    public Contact(int contactID, String contactName, String email){
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    private int contactID;
    private String contactName;
    private String email;

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
     * @return contactName name
     * */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName name
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return email email address
     * */
    public String getEmail() {
        return email;
    }

    /**
     * @param email email address
     * */
    public void setEmail(String email) {
        this.email = email;
    }
}
