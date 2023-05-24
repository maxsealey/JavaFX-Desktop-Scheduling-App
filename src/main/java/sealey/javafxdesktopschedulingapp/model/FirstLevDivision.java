package sealey.javafxdesktopschedulingapp.model;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class FirstLevDivision {

    /**
     * FirstLevDivision constructor
     *
     * @param divisionID id
     * @param divisionName name
     * @param createDate date added to db
     * @param createdBy user who added to db
     * @param lastUpdate date last updated
     * @param lastUpdatedBy user who last updated
     * */
    public FirstLevDivision(int divisionID, String divisionName, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    private int divisionID;
    private String divisionName;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    /**
     * @return divisionName name
     * */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName name
     * */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * @return divisionID id
     * */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID id
     * */
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
     * @param createdBy sets name of user who added to db
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
