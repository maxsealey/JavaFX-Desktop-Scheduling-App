package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
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
     * Returns the lowest available id number.
     * prev contains the id of the previous customer in iteration
     * ex. if newID is 4, and the previous id was 1, there is no item at 2 (due to deletion).
     * In this scenario, it would return 2. if there are no gaps, the newID would be the id
     * of last item incremented
     *
     * @return newID returns 1 if empty list, returns highest
     * */
    public static int getNextAppointmentID(){
        int newID = 1, prev = 1;

        if(AppointmentDAO.getAppointmentList().isEmpty()) return newID;

        for(Appointment a : AppointmentDAO.getAppointmentList()){
            newID = a.getAppointmentID();

            if(newID != prev + 1 && newID != 1){
                return prev + 1;
            }

            prev = a.getAppointmentID();
        }
        return newID + 1;
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
    public static int getNextCustomerID(){
        int newID = 1, prev = 1;

        if(CustomerDAO.getCustomerList().isEmpty()) return newID;

        for(Customer c : CustomerDAO.getCustomerList()){
            newID = c.getCustomerID();

            if(newID != prev + 1 && newID != 1){
                return prev + 1;
            }

            prev = c.getCustomerID();
        }
        return newID + 1;
    }
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

