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

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
