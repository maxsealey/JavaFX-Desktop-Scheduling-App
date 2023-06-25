package sealey.javafxdesktopschedulingapp.model;

/**
 * Description: Model class containing contact data
 *
 * @author maxsealey Sealey
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
     * ID getter
     *
     * @return contactID id
     * */
    public int getContactID() {
        return contactID;
    }

    /**
     * ID setter (unused)
     *
     * @param contactID id
     * */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Name getter
     *
     * @return contactName name
     * */
    public String getContactName() {
        return contactName;
    }

    /**
     * Name setter (unused)
     *
     * @param contactName name
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Email getter (unused)
     *
     * @return email email address
     * */
    public String getEmail() {
        return email;
    }

    /**
     * Email setter (unused)
     *
     * @param email email address
     * */
    public void setEmail(String email) {
        this.email = email;
    }
}
