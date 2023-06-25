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
 * Description: This class contains static methods to interact with the database on country and first-level division data
 *
 * @author maxsealey Sealey
 * */
public class LocationDAO {
    /**
     * Gets list of divisions from database and returns
     *
     * @return divisionList list
     * @throws SQLException
     * */
    public static ObservableList<FirstLevDivision> getDivisionList() throws SQLException {
        ObservableList<FirstLevDivision> list = FXCollections.observableArrayList();
        String sql = "SELECT Division_ID, Division, Country_ID FROM client_schedule.first_level_divisions";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next())
        {
            int divisionID = results.getInt("Division_ID");
            String divisionName = results.getString("Division");
            int countryID = results.getInt("Country_ID");
            list.add(new FirstLevDivision(divisionID, divisionName, countryID));
        }
        return list;
    }

    /**
     * Gets list of countries from database and returns
     *
     * @return countryList list
     * @throws SQLException
     * */
    public static ObservableList<Country> getCountryList() throws SQLException {
        ObservableList<Country> list = FXCollections.observableArrayList();

        String sql = "SELECT Country_ID, Country FROM client_schedule.countries";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next())
        {
            int countryID = results.getInt("Country_ID");
            String countryName = results.getString("Country");
            list.add(new Country(countryID, countryName));
        }
        return list;
    }

    /**
     * Uses division_ID FK from customer table and retrieves division and country names.
     * Called in CustomerDAO.populateCustomerList()
     *
     * @param division_id divisionID that points to customer FLD and Country names
     * @return location returns division name + country name concatenated
     * @throws SQLException
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
     * Finds division id that matches corresponding division name
     *
     * @param divisionName to get the id of
     * @return id matching id
     * @throws SQLException
     * */
    public static int getMatchDivisionID(String divisionName) throws SQLException {
        String sql = "SELECT Division_ID FROM client_schedule.first_level_divisions " +
                "WHERE Division = " + "'" + divisionName + "'";
        int id = 0;

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            id = results.getInt("Division_ID");
        }
        return id;
    }

    /**
     * Finds country id that matches corresponding country name
     *
     * @param countryName to get the id of
     * @return id matching id
     * @throws SQLException sql protection
     * */
    public static int getMatchCountryID(String countryName) throws SQLException {
        String sql = "SELECT Country_ID FROM client_schedule.countries " +
                "WHERE Country = " + "'" + countryName + "'";
        int id = 0;

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            id = results.getInt("Country_ID");
        }
        return id;
    }
}
