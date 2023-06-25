package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.dao.AppointmentDAO;
import sealey.javafxdesktopschedulingapp.dao.ContactDAO;
import sealey.javafxdesktopschedulingapp.dao.CustomerDAO;
import sealey.javafxdesktopschedulingapp.model.Appointment;
import sealey.javafxdesktopschedulingapp.model.Contact;
import sealey.javafxdesktopschedulingapp.model.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.time.*;

/**
 * Description: Contains some helper functions used in program to reduce clutter and increase readability
 *
 * @author maxsealey Sealey
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

    /**
     * Gets ComboBox value (e.g. 3 Geoffrey James) and splits it to get the customer/contact/user id
     *
     * @param comboBoxInput ComboBox value
     * @return int id
     * */
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

    /**
     * Records both successful and failed login attempts in login_activity.txt
     *
     * @param username attempted username input
     * @param successOrFail true if successful login, false if not
     * @throws IOException IOException
     * */
    public static void loginActivity(String username, boolean successOrFail) throws IOException {
        ZonedDateTime timestamp = ZonedDateTime.now();

        LocalDate date = timestamp.toLocalDate();
        LocalTime time = timestamp.toLocalTime();
        ZoneId zone = timestamp.getZone();

        String record = "";

        if(successOrFail){
            record = "Successful Login Attempt:: Username: " + username + "\n" + "Date: " + date + " " + "Time: " + time + " " + zone + "\n\n";
        } else {
            record = "Failed Login Attempt:: Username: " + username + "\n" + "Date: " + date + " " + "Time: " + time + " " + zone + "\n\n";
        }
        FileWriter output = new FileWriter("login_activity.txt", true);
        output.write(record);
        output.close();
    }

    /**
     * Filters appointments based on contact to generate report in TableView
     *
     * @param contactID selected contact
     * @return temp filtered list of appointments assigned to contact
     * */
    public static ObservableList<Appointment> filteredAppointmentsForReport(int contactID){
        ObservableList<Appointment> temp = FXCollections.observableArrayList();

        for(Appointment a : AppointmentDAO.getAppointmentList())
        {
            if(a.getContactID() == contactID){
                temp.add(a);
            }
        }
        return temp;
    }

    /**
     * Converts a contact id to a name
     *
     * @param id to convert
     * @return contactName converted
     * */
    public static String convertIDtoName(int id){
        try{
            for(Contact c : ContactDAO.getContactList()){
                if(c.getContactID() == id){
                    return c.getContactName();
                }
            }
        }catch(Exception e){
            System.out.println("Could not find contact.");
        }
        return null;
    }
}


