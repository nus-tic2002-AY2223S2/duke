package duke.Utility;

import duke.TasksType.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util {
    public static boolean checkMark(String input) {
        String[] splitted = input.split(" ");
        return splitted.length == 2;
    }
    public static String dateTimeToString(LocalDateTime formattedDT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formattedDT.format(formatter);
    }
    public static LocalDateTime convertDateTime(String dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formattedDT = LocalDateTime.parse(dt, formatter);
        return formattedDT;
    }
    public static String displayDT(LocalDateTime formattedDT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return formattedDT.format(formatter);
    }
    public static Task.priorityLevel stringToLevel(String s) {
        switch (s.toUpperCase()) {
            case "LOW":
                return Task.priorityLevel.Low;
            case "HIGH":
                return Task.priorityLevel.High;
            default:
                return Task.priorityLevel.Medium;
        }
    }
}
