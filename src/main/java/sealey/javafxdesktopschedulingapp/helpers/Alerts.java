package sealey.javafxdesktopschedulingapp.helpers;

import javafx.scene.control.Alert;

import java.util.Locale;
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
     * @param title Alert title
     * @param content Alert message
     * @param type Alert type
     * */
    public static void message(String title, String content, Alert.AlertType type) {

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays message in either english or french for invalid login credentials
     * */
    public static void invalidCredentials() {
        ResourceBundle langs =  ResourceBundle.getBundle("lang", Locale.getDefault());
        String title = langs.getString("loginFailAlertTitle");
        String content = langs.getString("loginFailAlert");

        message(title,content, Alert.AlertType.ERROR);
    }
}
