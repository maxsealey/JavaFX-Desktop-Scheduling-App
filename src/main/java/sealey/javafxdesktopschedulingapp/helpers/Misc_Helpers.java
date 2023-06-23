package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class Misc_Helpers {

    /**
     *
     * */

    /**
     *
     * */
    public static ObservableList<String> splitLocation(String sub){
        String comma = String.valueOf(',');
        int dividerIndex = sub.indexOf(comma);
        ObservableList<String> list = FXCollections.observableArrayList();

        list.add(sub.substring(0,dividerIndex));
        list.add(sub.substring(dividerIndex + 2));

        return list;
    }
}
