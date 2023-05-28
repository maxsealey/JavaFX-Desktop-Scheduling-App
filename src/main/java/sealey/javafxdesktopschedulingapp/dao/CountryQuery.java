package sealey.javafxdesktopschedulingapp.dao;

import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {
    /**
     * @param countryID id
     * @param countryName name
     * */
    public static int insert(String countryName, int countryID) throws SQLException {
        String sql = "INSERT INTO COUNTRIES (Country, Country_ID) VALUES(?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, countryName);
        ps.setInt(2, countryID);

        return ps.executeUpdate();
    }

    /**
     *
     * */
    public static int update(String countryName, int countryID) throws SQLException {
        String sql = "UPDATE COUNTRIES SET Country = ? WHERE Country_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, countryName);
        ps.setInt(2, countryID);

        return ps.executeUpdate();
    }

    /**
     *
     * */
    public static int delete(int countryID) throws SQLException {
        String sql = "DELETE FROM COUNTRIES WHERE CountryID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, countryID);

        return ps.executeUpdate();
    }

    /**
     *
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM COUNTRIES";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int countryID = rs.getInt("CountryID");
            String countryName = rs.getString("Country");
        }
    }
    /**
     *
     */
    public static void select(int countryID) throws SQLException {
        String sql = "SELECT * FROM COUNTRIES";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int country_ID = rs.getInt("CountryID");
            String countryName = rs.getString("Country");
        }
    }
}
