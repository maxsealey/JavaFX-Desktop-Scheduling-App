package sealey.javafxdesktopschedulingapp.model;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class Country {

    /**
     * Country class constructor
     *
     * @param countryID id
     * @param country name
     * @param createDate date added to db
     * @param createdBy user who added to db
     * @param lastUpdate date last updated
     * @param lastUpdatedBy user who last updated
     * */
    public Country(int countryID, String country, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {

        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    private int countryID;
    private String country;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    /**
     * @return countryID id
     * */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @param countryID id
     * */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * @return country name
     * */
    public String getCountry() {
        return country;
    }

    /**
     * @param country name
     * */
    public void setCountry(String country) {
        this.country = country;
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
