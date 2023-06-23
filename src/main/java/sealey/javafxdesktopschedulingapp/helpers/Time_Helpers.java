package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.time.*;

public class Time_Helpers {
    /**
     *
     * */
//    public static ZonedDateTime utcToLocal(ZonedDateTime utc){
//
//    }

    /**
     *
     * */
    public static ZonedDateTime localToUTC(LocalDateTime local){
        ZonedDateTime localZDT = local.atZone(ZoneId.systemDefault());
        return localZDT.withZoneSameInstant(ZoneId.of("UTC"));
    }

    /**
     *
     * */
//    public static ZonedDateTime localToEST(LocalDateTime local){
//
//    }

    /**
     * Sets list of times into ComboBoxes
     *
     * @param localTimes ComboBox to contain the times
     * @param startOrEnd String which input will either be 'StartOrEnd'
     * */
    public static void setTimesInComboBoxes(ComboBox<LocalTime> localTimes, String startOrEnd){
        ObservableList<LocalTime> times = FXCollections.observableArrayList();

        // should simplify using lambda
        for(int i = 0; i < 24; i++){
            for(int j = 0; j < 60; j += 5){
                times.add(LocalTime.of(i,j));
            }
        }

        localTimes.setItems(times);
        localTimes.setVisibleRowCount(5);
        localTimes.setPromptText(startOrEnd);

    }

    /**
     *
     * */
    public static void timeValidityChecker(LocalTime start, LocalTime end){

    }
}
