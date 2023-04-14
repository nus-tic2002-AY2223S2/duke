package duke.Utility;

import duke.Exception.DukeException;
import duke.TasksType.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    /**
     * Returns a boolean if the mark/unmark input is valid
     * @param input takes in a string input from user and check if it has a word and another string (num) behind
     * @return true if only there are two words
     */
    public static boolean checkMark(String input) {
        String[] splitted = input.split(" ");
        return splitted.length == 2;
    }

    /**
     * Returns a String of correct dates and times format
     * @param formattedDT takes in a LocalDateTime formatted input
     * @return a string in "yyyy-MM-dd HH:mm"
     */
    public static String dateTimeToString(LocalDateTime formattedDT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formattedDT.format(formatter);
    }

    /**
     * Returns a LocalDateTime in correct format
     * @param dt takes in a LocalDateTime input with default format
     * @return a LocalDateTime variable
     */
    public static LocalDateTime convertDateTime(String dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formattedDT = LocalDateTime.parse(dt, formatter);
        return formattedDT;
    }

    /**
     * Returns a String displays the required LocalDateTime into string
     * @param formattedDT takes in the formatted LocalDateTime input
     * @return a string of the formatted LocalDateTime
     */
    public static String displayDT(LocalDateTime formattedDT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return formattedDT.format(formatter);
    }

    /**
     * Returns a PriorityLevel that was converted from a String
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
     * Validates the dateTime ensuring that it is not in the past
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
     * Validates the start and end dateTime in the Event task
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
