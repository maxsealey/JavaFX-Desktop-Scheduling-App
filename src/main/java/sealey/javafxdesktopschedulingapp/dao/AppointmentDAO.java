package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.helpers.Time_Helpers;
import sealey.javafxdesktopschedulingapp.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Description: This class contains static methods to interact with the database on appointment data
 *
 * @author maxsealey Sealey
 * */
public class AppointmentDAO {
    /**
     * @return appointmentList gets list of appointments from database and returns
     * @throws SQLException
     * */
    public static ObservableList<Appointment> getAppointmentList() throws SQLException {
        ObservableList<Appointment> list = FXCollections.observableArrayList();

        String sql = "SELECT Appointment_ID, Customer_ID, User_ID, Contact_ID, Title, Description" +
                ", Location, Type, Start, End FROM client_schedule.appointments;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while (results.next()) {
            int appointmentID = results.getInt("Appointment_ID");
            int customerID = results.getInt("Customer_ID");
            int userID = results.getInt("User_ID");
            int contactID = results.getInt("Contact_ID");
            String title = results.getString("Title");
            String desc = results.getString("Description");
            String location = results.getString("Location");
            String type = results.getString("Type");
            LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = results.getTimestamp("End").toLocalDateTime();

            start = LocalDateTime.from(Time_Helpers.utcToLocal(start));
            end = LocalDateTime.from(Time_Helpers.utcToLocal(end));

            list.add(new Appointment(appointmentID, customerID, userID, contactID, title, desc, location, type, start, end));
        }
        return list;
    }

    /**
     * Inserts a new appointment into the database
     *
     * @param newAppointment appointment to insert
     * @return int ps.executeUpdate()
     * @throws SQLException sql protection
     * */
    public static int insertAppointment(Appointment newAppointment) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Appointment_ID, Customer_ID, User_ID, Contact_ID, Title, Description" +
                ", Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, newAppointment.getAppointmentID());
        ps.setInt(2, newAppointment.getCustomerID());
        ps.setInt(3, newAppointment.getUserID());
        ps.setInt(4, newAppointment.getContactID());
        ps.setString(5, newAppointment.getTitle());
        ps.setString(6, newAppointment.getDescription());
        ps.setString(7, newAppointment.getLocation());
        ps.setString(8, newAppointment.getType());

        ps.setTimestamp(9, Timestamp.valueOf(newAppointment.getStartDateTime()));
        ps.setTimestamp(10, Timestamp.valueOf(newAppointment.getEndDateTime()));

        ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(12, UserDAO.getCurrentUser().getUsername());
        ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(14, UserDAO.getCurrentUser().getUsername());

        return ps.executeUpdate();
    }

    /**
     * Deletes appointment from the database
     *
     * @param appointmentID to be deleted
     * @throws SQLException sql protection
     * */
    public static void deleteAppointment(int appointmentID) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, appointmentID);

        ps.executeUpdate();
    }

    /**
     * Updates pre-existing appointment in the database
     *
     * @param appointment to be updated
     * @throws SQLException sql protection
     * */
    public static void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Customer_ID = ?, User_ID = ?, Contact_ID = ?, Title = ?, Description = ?" +
                ", Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ? WHERE Appointment_ID = ?";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, appointment.getCustomerID());
        ps.setInt(2, appointment.getUserID());
        ps.setInt(3, appointment.getContactID());
        ps.setString(4, appointment.getTitle());
        ps.setString(5, appointment.getDescription());
        ps.setString(6, appointment.getLocation());
        ps.setString(7, appointment.getType());

        ps.setTimestamp(8, Timestamp.valueOf(appointment.getStartDateTime()));
        ps.setTimestamp(9, Timestamp.valueOf(appointment.getEndDateTime()));

        ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(11, UserDAO.getCurrentUser().getUsername());
        ps.setInt(12, appointment.getAppointmentID());

        ps.executeUpdate();
    }

    /**
     * Retrieves data for all appointments this month
     *
     * @return appointmentsThisMonth list of retrieved appointments
     * @throws SQLException
     * */
    public static ObservableList<Appointment> getAppointmentsThisMonth() throws SQLException {
        ObservableList<Appointment> appointmentsThisMonth = FXCollections.observableArrayList();
        String sql = "SELECT Appointment_ID, Customer_ID, User_ID, Contact_ID, Title, Description" +
                ", Location, Type, Start, End FROM client_schedule.appointments WHERE MONTH(Start) = MONTH(CURDATE())";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next())
        {
            int appointmentID = results.getInt("Appointment_ID");
            int customerID = results.getInt("Customer_ID");
            int userID = results.getInt("User_ID");
            int contactID = results.getInt("Contact_ID");
            String title = results.getString("Title");
            String desc = results.getString("Description");
            String location = results.getString("Location");
            String type = results.getString("Type");
            LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = results.getTimestamp("End").toLocalDateTime();

            start = LocalDateTime.from(Time_Helpers.utcToLocal(start));
            end = LocalDateTime.from(Time_Helpers.utcToLocal(end));

            appointmentsThisMonth.add(new Appointment(appointmentID, customerID, userID, contactID, title, desc, location, type, start, end));
        }

        return appointmentsThisMonth;
    }

    /**
     * Retrieves data for all appointments this week
     *
     * @return appointmentsThisWeek list of retrieved appointments
     * @throws SQLException
     * */
    public static ObservableList<Appointment> getAppointmentsThisWeek() throws SQLException {
        ObservableList<Appointment> appointmentsThisWeek = FXCollections.observableArrayList();
        String sql = "SELECT Appointment_ID, Customer_ID, User_ID, Contact_ID, Title, Description" +
                ", Location, Type, Start, End FROM client_schedule.appointments WHERE WEEK(Start) = WEEK(CURDATE())";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next())
        {
            int appointmentID = results.getInt("Appointment_ID");
            int customerID = results.getInt("Customer_ID");
            int userID = results.getInt("User_ID");
            int contactID = results.getInt("Contact_ID");
            String title = results.getString("Title");
            String desc = results.getString("Description");
            String location = results.getString("Location");
            String type = results.getString("Type");
            LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = results.getTimestamp("End").toLocalDateTime();

            start = LocalDateTime.from(Time_Helpers.utcToLocal(start));
            end = LocalDateTime.from(Time_Helpers.utcToLocal(end));

            appointmentsThisWeek.add(new Appointment(appointmentID, customerID, userID, contactID, title, desc, location, type, start, end));
        }
        return appointmentsThisWeek;
    }
}
