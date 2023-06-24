package sealey.javafxdesktopschedulingapp.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

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
     *
     * */
    public static ZonedDateTime localToEST(LocalDateTime local){
        ZonedDateTime localZDT = local.atZone(ZoneId.systemDefault());
        return localZDT.withZoneSameInstant(ZoneId.of("US/Eastern"));
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
        ZonedDateTime startEstZDT = localToEST(localStart);
        ZonedDateTime endEstZDT = localToEST(localEnd);

        LocalTime startTime = startEstZDT.toLocalTime();
        LocalTime endTime = endEstZDT.toLocalTime();

        LocalTime open = LocalTime.of(8,0);
        LocalTime close = LocalTime.of(22,0);

        if(startEstZDT.isAfter(endEstZDT) || startEstZDT.isEqual(endEstZDT)){
            return false;
        }

        return !startTime.isAfter(close) && !endTime.isAfter(close) && !startTime.isBefore(open) && !endTime.isBefore(open);
    }
}
