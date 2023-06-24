package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.model.Appointment;
import sealey.javafxdesktopschedulingapp.model.Contact;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class Misc_Helpers {

    /**
     * Takes a string in the form of a location (e.g. Arkansas, US), and splits it be the comma,
     * returning an Observable list with 2 items: Arkansas, US
     *
     * @param sub string to be divided
     * @return list ObservableList with 2 elements
     * */
    public static ObservableList<String> splitLocation(String sub){
        String comma = String.valueOf(',');
        int dividerIndex = sub.indexOf(comma);
        ObservableList<String> list = FXCollections.observableArrayList();

        list.add(sub.substring(0,dividerIndex));
        list.add(sub.substring(dividerIndex + 2));

        return list;
    }

    public static int splitID(String comboBoxInput){
        String space = String.valueOf(' ');
        int dividerIndex = comboBoxInput.indexOf(space);

        return Integer.parseInt(comboBoxInput.substring(0,dividerIndex));
    }

    /**
     * returns false if no
     * */
//    public static boolean appointmentOverlap(Customer reqCustomer, Contact reqContact, LocalDateTime reqStart, LocalDateTime reqEnd) throws SQLException {
//        AppointmentDAO.populateAppointmentList();
//        ObservableList<Appointment> appointments = AppointmentDAO.getAppointmentList();
//
//
//
//    }

}
