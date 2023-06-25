package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsDAO {
    public static ObservableList<String> typeList = FXCollections.observableArrayList();

    public static void populateTypeList() throws SQLException {
        String sql = "SELECT DISTINCT Type FROM client_schedule.appointments";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        typeList.clear();
        while(results.next()){
            String type = results.getString("Type");
            typeList.add(type);
        }
    }

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

    public static ObservableList<String> getTypeList() {
        return typeList;
    }

}
