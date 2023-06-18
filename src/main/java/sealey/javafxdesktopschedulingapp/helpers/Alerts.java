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
     * Calls message() and creates simple alert
     *
     * @param content message to be displayed
     */
    public static void simpleMessage(String content) {
        message("Something went wrong.", content, Alert.AlertType.ERROR);
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
        alert.setHeaderText("Are you sure you want to delete this customer?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
