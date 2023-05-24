package sealey.javafxdesktopschedulingapp.model;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class User {
    
    /**
     * User class constructor
     *
     * @param username username
     * @param userID id
     * @param createDate date added to db
     * @param createdBy user who added to db
     * @param lastUpdate date last updated
     * @param lastUpdatedBy user who last updated
     * */
    public User(int userID, String username, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy){
        this.userID = userID;
        this.username = username;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    private int userID;
    private String username;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    
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
     * @return username name
     * */
    public String getUsername() {
        return username;
    }

    /**
     * @param username name
     * */
    public void setUsername(String username) {
        this.username = username;
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
