package duke.Utility;

import duke.DukeException;
import duke.TasksType.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {

    /**
     * This method checks if the mark/unmark input is valid
     * @param input takes in a string input from user and check if it has a word and another string (num) behind
     * @return true if only there are two words
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
     * @return a LocalDateTime variable
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

    /**
     * This method validates the dateTime ensuring that it is not in the past
     * @param dateTime takes in a LocalDateTime input
     * @throws DukeException if the time is before current time
     */
    public static void validateDateTime(LocalDateTime dateTime) throws DukeException {

        LocalDateTime currentDateTime = LocalDateTime.now();
        if (dateTime.isBefore(currentDateTime)) {
            throw new DukeException("☹ OOPS!!! Date is in the past! :-(");
        }
    }

    /**
     * This method validates the start and end dateTime in the Event task
     * such that the end dateTime will never be before start dateTime
     * @param dateTimeStart takes in a LocalDateTime as the start dateTime
     * @param dateTimeEnd takes in a LocalDateTime as the end dateTime
     * @throws DukeException if the end dateTime is before start dateTime
     */
    public static void validateEventDate(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) throws DukeException{
        if (dateTimeEnd.isBefore(dateTimeStart)) {
            throw new DukeException("☹ OOPS!!! Start dateTime is after End dateTime! :-(");
        }
    }
}
