package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: This class contains static methods to interact with the database to generate reports
 *
 * @author maxsealey Sealey
 * */
public class ReportsDAO {

    /**
     * Counts total appointments with specified type
     *
     * @param type to check
     * @return count number of appointments
     * @throws SQLException
     * */
    public static int getTotalOfType(String type) throws SQLException {
        int count = -1;
        String sql = "SELECT COUNT(Type) AS count FROM client_schedule.appointments WHERE Type = " + type;
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            count = results.getInt("count");
        }
        return count;
    }

    /**
     * Gets list of distinct types from database and returns
     *
     * @return list of types
     * @throws SQLException sql protection
     * */
    public static ObservableList<String> getTypeList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT Type FROM client_schedule.appointments";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            String type = results.getString("Type");
            list.add(type);
        }
        return list;
    }

    public static int getTotalForMonth(int month) throws SQLException {
        int count = -1;
        String sql = "SELECT COUNT(Appointment_ID) AS monthCount FROM client_schedule.appointments WHERE MONTH(Start) = " + month;
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            count = results.getInt("monthCount");
        }
        return count;
    }
}
