package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.Appointment;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDAO {
    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();


    public static ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public static void setAppointmentList(ObservableList<Appointment> appointmentList) {
        AppointmentDAO.appointmentList = appointmentList;
    }

    public static void populateAppointmentList() throws SQLException {
        String sql = "SELECT Appointment_ID, Customer_ID, User_ID, Contact_ID, Title, Description" +
                ", Location, Type, Start, End FROM client_schedule.appointments;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();
        appointmentList.clear();

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

            appointmentList.add(new Appointment(appointmentID, customerID, userID, contactID, title, desc, location, type, start, end));
        }
    }

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

    public static void deleteAppointment(int appointmentID) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, appointmentID);

        ps.executeUpdate();
    }

    /**
     * Returns the lowest available id number.
     * prev contains the id of the previous customer in iteration
     * ex. if newID is 4, and the previous id was 1, there is no item at 2 (due to deletion).
     * In this scenario, it would return 2. if there are no gaps, the newID would be the id
     * of last item incremented
     *
     * @return newID returns 1 if empty list, returns highest
     * */
    public static int getNextID(){
        int newID = 1, prev = 1;

        if(appointmentList.isEmpty()) return newID;

        for(Appointment a : appointmentList){
            newID = a.getAppointmentID();

            if(newID != prev + 1 && newID != 1){
                return prev + 1;
            }

            prev = a.getAppointmentID();
        }
        return newID + 1;
    }
}
