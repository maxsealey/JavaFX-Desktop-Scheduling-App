package sealey.javafxdesktopschedulingapp.model;

/**
 * Description: Model class containing country data
 *
 * @author maxsealey Sealey
 * */
public class Country {

    /**
     * Country class constructor
     *
     * @param countryID id
     * @param country name
     * */
    public Country(int countryID, String country) {

        this.countryID = countryID;
        this.country = country;
    }

    private int countryID;
    private String country;

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
}
