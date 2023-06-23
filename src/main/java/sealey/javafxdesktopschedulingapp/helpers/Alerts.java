package sealey.javafxdesktopschedulingapp.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class Alerts {
    /**
     * displays alert and that sets title, content, and alert type
     *
     * @param title   Alert title
     * @param content Alert message
     * @param type    Alert type
     */
    public static void message(String title, String content, Alert.AlertType type) {

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays message in either english or french for invalid login credentials
     */
    public static void invalidCredentials() {
        ResourceBundle langs = ResourceBundle.getBundle("lang", Locale.getDefault());
        String title = langs.getString("loginFailAlertTitle");
        String content = langs.getString("loginFailAlert");

        message(title, content, Alert.AlertType.ERROR);
    }

    /**
     * Displays alert asking for confirmation that item should be deleted, returns true if Ok button clicked, false otherwise
     *
     * @return boolean true or false
     * */
    public static boolean deleteConfirmation(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirm Deletion");
        alert.setContentText("Click 'Ok' to proceed.");
        alert.setHeaderText("Are you sure you want to delete?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }

    /**
     * Displays alert asking for confirmation that customer should be added
     *
     * @return boolean true or false
     * */
    public static boolean confirmSave(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirm Save");
        alert.setContentText("Click 'Ok' to add to the database.");
        alert.setHeaderText("Is all of the data correct?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }

    /**
     * Displays alert asking for confirmation that customer should be added
     *
     * @return boolean true or false
     * */
    public static boolean confirmCancel(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirm Cancel");
        alert.setContentText("Click 'Ok' to go back to dashboard.");
        alert.setHeaderText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }

    /**
     * Error message displayed when there is an attempt to place appointment outside business hours.
     * */
    public static void businessHoursAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Could not place the appointment.");
        alert.setContentText("Make sure that the start and end times are within business hours (8am - 10pm EST), " +
                "and that the appointment start time is before the end time.");
        alert.showAndWait();
    }

    /**
     * Error message displayed when there is an attempt to place appointment that's time conflicts with another appointment.
     * */
    public static void overlappingAppointmentsAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Could not make the appointment.");
        alert.setHeaderText("This appointment conflicts with another placed appointment.");
        alert.setContentText("Please enter valid times and try again.");
        alert.showAndWait();
    }
}
