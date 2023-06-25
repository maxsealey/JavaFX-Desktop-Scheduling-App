package sealey.javafxdesktopschedulingapp.model;

/**
 * Description: Model class containing first-level division data
 *
 * @author maxsealey Sealey
 * */
public class FirstLevDivision {

    /**
     * FirstLevDivision constructor
     *
     * @param divisionID id
     * @param divisionName name
     * */
    public FirstLevDivision(int divisionID, String divisionName, int countryID){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    private int divisionID;
    private String divisionName;
    private int countryID;

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
}
