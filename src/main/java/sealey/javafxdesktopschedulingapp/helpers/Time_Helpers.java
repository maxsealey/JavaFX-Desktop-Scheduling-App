package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.dao.ContactDAO;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.model.Appointment;
import sealey.javafxdesktopschedulingapp.model.Contact;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.SQLException;
import java.time.*;

public class Time_Helpers {
    /**
     *
     * */
    public static ZonedDateTime utcToLocal(LocalDateTime utc){
        ZonedDateTime utcZDT = utc.atZone(ZoneId.of("UTC"));
        return utcZDT.withZoneSameInstant(ZoneId.systemDefault());
    }

    /**
     *
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
     * */
    public static void setTimesInComboBoxes(ComboBox<LocalTime> localTimes, String startOrEnd, int addOrUpdate){
        ObservableList<LocalTime> times = FXCollections.observableArrayList();

        // should simplify using lambda
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
     *
     * */
    public static boolean timeValidityCheck(LocalDateTime localStart, LocalDateTime localEnd) throws SQLException {
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

        if (!startEstZDT.toLocalDate().equals(endEstZDT.toLocalDate())) {
            return false;
        }

        if (startEstZDT.isAfter(endEstZDT) || startEstZDT.isEqual(endEstZDT)) {
            return false; // Start time is after end time
        }

        if(startTime.isBefore(LocalTime.from(open)) || endTime.isAfter(LocalTime.from(close))){
            return false;
        }
        return true;
    }

    /**
     *
     * */
    public static boolean checkCustomerOverlap(int customerID, LocalDateTime start, LocalDateTime end){
        for(Customer c : CustomerDAO.getCustomerList()){
            for(Appointment a : AppointmentDAO.getAppointmentList()){
                if((a.getCustomerID() == c.getCustomerID()) && (c.getCustomerID() == customerID)){
                    if((a.getStartDateTime().isBefore(start) || a.getStartDateTime().isEqual(start)) && a.getEndDateTime().isAfter(start)){
                        return false;
                    }
                    if(a.getStartDateTime().isAfter(start) && a.getStartDateTime().isBefore(end)){
                        return false;
                    }
                }
            }
        }
        return true;
    }







}
