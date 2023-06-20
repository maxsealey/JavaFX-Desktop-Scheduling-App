package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.Country;
import sealey.javafxdesktopschedulingapp.model.FirstLevDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class LocationDAO {
    private static ObservableList<FirstLevDivision> divisionList = FXCollections.observableArrayList();
    private static ObservableList<Country> countryList = FXCollections.observableArrayList();

    /**
     * Division list getter
     *
     * @return divisionList whole list
     * */
    public static ObservableList<FirstLevDivision> getDivisionList() {
        return divisionList;
    }

    /**
     * Country list getter
     *
     * @return countryList whole list
     * */
    public static ObservableList<Country> getCountryList() {
        return countryList;
    }

    /**
     * Retrieves division data from database, creates objects, adds them to divisionList
     * */
    public static void populateDivisionList() throws SQLException {
        String sql = "SELECT Division_ID, Division FROM client_schedule.first_level_divisions";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next())
        {
            int divisionID = results.getInt("Division_ID");
            String divisionName = results.getString("Division");
            divisionList.add(new FirstLevDivision(divisionID, divisionName));
        }
    }

    /**
     * Retrieves country data from database, creates objects, adds them to countryList
     * */
    public static void populateCountryList() throws SQLException {
        String sql = "SELECT Country_ID, Country FROM client_schedule.countries";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next())
        {
            int countryID = results.getInt("Country_ID");
            String countryName = results.getString("Country");
            countryList.add(new Country(countryID, countryName));
        }
    }

    /**
     * Uses division_ID FK from customer table and retrieves division and country names.
     * Called in CustomerDAO.populateCustomerList()
     *
     * @param division_id divisionID that points to customer FLD and Country names
     * @return location returns division name + country name concatenated
     * */
    public static String getLocation(int division_id) throws SQLException {
        String sql = "SELECT FD.Division, C.Country FROM client_schedule.customers AS CUS " +
                "RIGHT JOIN client_schedule.first_level_divisions AS FD ON CUS.Division_ID = FD.Division_ID " +
                "RIGHT JOIN client_schedule.countries AS C ON FD.Country_ID = C.Country_ID " +
                "WHERE FD.Division_ID = " + division_id;

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();
        String location = "";

        while(results.next()){
            // will always only contain one result unless duplicate primary key error (shouldn't happen)
            location = results.getString("Division") + ", " + results.getString("Country");
        }
        return location;
    }

    /**
     *
     * */
    public static int getDivisionID(String divisionName) throws SQLException {
        int id = 0;
        String sql = "SELECT Division_ID FROM client_schedule.first_level_divisions " +
                "WHERE Division = " + "'" + divisionName + "'";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            id = results.getInt("Division_ID");
        }
        return id;
    }
}
