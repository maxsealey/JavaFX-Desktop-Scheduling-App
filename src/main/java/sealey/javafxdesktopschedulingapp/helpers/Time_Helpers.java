package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.model.Appointment;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.SQLException;
import java.time.*;


/**
 * Description: Contains time-related helper functions used in program to reduce clutter and increase readability
 *
 * @author maxsealey Sealey
 * */
public class Time_Helpers {
    /**
     * Converts UTC timestamp to local time
     *
     * @param utc UTC date and time to be converted
     * @return ZonedDateTime local ZDT
     * */
    public static ZonedDateTime utcToLocal(LocalDateTime utc){
        ZonedDateTime utcZDT = utc.atZone(ZoneId.of("UTC"));
        return utcZDT.withZoneSameInstant(ZoneId.systemDefault());
    }

    /**
     * Converts local timestamp to utc time
     *
     * @param local date and time to be converted
     * @return ZonedDateTime UTC ZDT
     * */
    public static ZonedDateTime localToUTC(LocalDateTime local){
        ZonedDateTime localZDT = local.atZone(ZoneId.systemDefault());
        return localZDT.withZoneSameInstant(ZoneId.of("UTC"));
    }

    /**
     * Sets list of times into ComboBoxes
     *
     * @param localTimes ComboBox to contain the times
     * @param startOrEnd String which input will either be 'StartOrEnd'
     * @param addOrUpdate 1 if adding, 0 if modifying
     * */
    public static void setTimesInComboBoxes(ComboBox<LocalTime> localTimes, String startOrEnd, int addOrUpdate){
        ObservableList<LocalTime> times = FXCollections.observableArrayList();

        for(int i = 0; i < 24; i++){
            for(int j = 0; j < 60; j += 5){
                times.add(LocalTime.of(i,j));
            }
        }

        localTimes.setItems(times);
        localTimes.setVisibleRowCount(5);
        if(addOrUpdate == 0){
            localTimes.setPromptText(startOrEnd);
        }
    }

    /**
     * Checks whether input times are valid in EST (start before end, within business hours)
     *
     * @param localStart check start time
     * @param localEnd check end time
     * @return false if invalid, true if valid
     * */
    public static boolean timeValidityCheck(LocalDateTime localStart, LocalDateTime localEnd) {
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        ZonedDateTime startLocalZDT = localStart.atZone(localZone);
        ZonedDateTime startEstZDT = startLocalZDT.withZoneSameInstant(estZone);
        ZonedDateTime endLocalZDT = localEnd.atZone(localZone);
        ZonedDateTime endEstZDT = endLocalZDT.withZoneSameInstant(estZone);

        LocalTime startTime = startEstZDT.toLocalTime();
        LocalTime endTime = endEstZDT.toLocalTime();

        LocalTime open = LocalTime.of(8, 0);
        LocalTime close = LocalTime.of(22, 0);

        // executes if the start date and end date are on different days
        if (!startEstZDT.toLocalDate().equals(endEstZDT.toLocalDate())) {
            return false;
        }

        // executes if start and end are the same or start comes after end
        if (startEstZDT.isAfter(endEstZDT) || startEstZDT.isEqual(endEstZDT)) {
            return false;
        }

        // executes if times aren't within business hours
        if(startTime.isBefore(LocalTime.from(open)) || endTime.isAfter(LocalTime.from(close))){
            return false;
        }
        return true;
    }

    /**
     * Checks whether a customer has a pre-existing appointment that would overlap with new appointment
     *
     * @param checkOverlapList list of appointments to iterate through
     * @param customerID customer to check
     * @param start check start time
     * @param end check end time
     * @throws SQLException
     * */
    public static boolean checkCustomerOverlap(ObservableList<Appointment> checkOverlapList, int customerID, LocalDateTime start, LocalDateTime end) throws SQLException {
        for(Customer c : CustomerDAO.getCustomerList()){
            for(Appointment a : checkOverlapList){ // needs to run through parameter list so ModifyAppointment can pass in shortened list
                if((a.getCustomerID() == c.getCustomerID()) && (c.getCustomerID() == customerID)){
                    if((a.getStartDateTime().isBefore(start) || a.getStartDateTime().isEqual(start)) && a.getEndDateTime().isAfter(start)){
                        return false; // timeValidityChecker runs first
                    }
                    if(a.getStartDateTime().isAfter(start) && a.getStartDateTime().isBefore(end)){
                        return false; // runs on edge case that new appointment is enveloped by pre-existing appointment
                    }
                }
            }
        }
        return true;
    }
}
