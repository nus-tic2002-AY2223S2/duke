package duke.Utility;

import duke.TasksType.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    /**
     * This method checks if the mark/unmark input is valid
     * @param input takes in a string input from user and check if it has a word and another string (num) behind
     * @return
     */
    public static boolean checkMark(String input) {
        String[] splitted = input.split(" ");
        return splitted.length == 2;
    }

    /**
     * This method changes dates and times to string format
     * @param formattedDT takes in a LocalDateTime formatted input
     * @return a string in "yyyy-MM-dd HH:mm"
     */
    public static String dateTimeToString(LocalDateTime formattedDT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formattedDT.format(formatter);
    }

    /**
     * This method converts the default LocalDateTime format into the required format
     * @param dt takes in a LocalDateTime input with default format
     * @return
     */
    public static LocalDateTime convertDateTime(String dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formattedDT = LocalDateTime.parse(dt, formatter);
        return formattedDT;
    }

    /**
     * This method displays the required LocalDateTime into string
     * @param formattedDT takes in the formatted LocalDateTime input
     * @return a string of the formatted LocalDateTime
     */
    public static String displayDT(LocalDateTime formattedDT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return formattedDT.format(formatter);
    }

    /**
     * This method converts the string into priority level
     * @param s takes in a string input
     * @return a priorityLevel
     */
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
