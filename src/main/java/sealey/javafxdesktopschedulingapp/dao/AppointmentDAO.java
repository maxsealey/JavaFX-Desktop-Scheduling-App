package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.model.Appointment;

public class AppointmentDAO {
    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();


    public static ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public static void setAppointmentList(ObservableList<Appointment> appointmentList) {
        AppointmentDAO.appointmentList = appointmentList;
    }
}

// LECTURE NOTES FOR REFERENCE:
//
//
//    public static int insert(String countryName, int countryID) throws SQLException {
//        String sql = "INSERT INTO COUNTRIES (Country, Country_ID) VALUES(?, ?)";
//        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//
//        ps.setString(1, countryName);
//        ps.setInt(2, countryID);
//
//        return ps.executeUpdate();
//    }
//
//
//    public static int update(String countryName, int countryID) throws SQLException {
//        String sql = "UPDATE COUNTRIES SET Country = ? WHERE Country_ID = ?";
//        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//
//        ps.setString(1, countryName);
//        ps.setInt(2, countryID);
//
//        return ps.executeUpdate();
//    }
//
//
//    public static int delete(int countryID) throws SQLException {
//        String sql = "DELETE FROM COUNTRIES WHERE CountryID = ?";
//        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//
//        ps.setInt(1, countryID);
//
//        return ps.executeUpdate();
//    }
//
//
//    public static void select() throws SQLException {
//        String sql = "SELECT * FROM COUNTRIES";
//        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        while(rs.next()) {
//            int countryID = rs.getInt("CountryID");
//            String countryName = rs.getString("Country");
//        }
//    }
//
//    public static void select(int countryID) throws SQLException {
//        String sql = "SELECT * FROM COUNTRIES";
//        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        while(rs.next()) {
//            int country_ID = rs.getInt("CountryID");
//            String countryName = rs.getString("Country");
//        }
//    }