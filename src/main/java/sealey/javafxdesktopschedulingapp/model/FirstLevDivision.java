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
    public FirstLevDivision(int divisionID, String divisionName){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    private int divisionID;
    private String divisionName;

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
}
