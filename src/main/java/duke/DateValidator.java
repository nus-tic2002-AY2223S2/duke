package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Event;

public class DateValidator {

    /**
     *  Attribute
     */
    private static String dateFormat = "yyyy-MM-dd HH:mm";
    private static String displayFormat = "dd MMM yyyy HH:mm";

    /**
     *  Constructor
     */
    public DateValidator() {

    }

    /**
     * validateInput method will validate the date input by user to fix it to a format
     * @param dateString date input in String datatype
     * @return TRUE if date format is valid, else FALSE
     * @throws DateTimeParseException if date input is in wrong format
     */
    public static boolean validateInput(String dateString) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDateTime dt = LocalDateTime.parse(dateString, df);
            return true;
        } catch (DateTimeParseException e) {
            //handle exception
            return false;
        }
    }

    /**
     * convertStringToDate method will convert a string input into a LocalDateTime variable date using the same conversion
     * @param dateString date input in String datatype
     * @return a LocalDateTime variable after conversion
     * @throws DateTimeParseException if date input is in the wrong format
     */
    public static LocalDateTime convertStringToDate(String dateString) throws DukeException {
        if(validateInput(dateString))
        {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
            LocalDateTime dateFormatted = LocalDateTime.parse(dateString, df);
            return dateFormatted;
        }else {
            throw new DukeException("Date Format Error");
        }
    }

    /**
     * convertDateToString method will convert a LocalDateTime input into a formatted String output for date
     * @param lclDateTime date input in LocalDateTime datatype
     * @return a formatted date in String format
     */
    public static String convertDateToString(LocalDateTime lclDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        return lclDateTime.format(df);
    }

    /**
     * convertDateToDisplay method will convert a LocalDateTime into a formatted String output for display purposes
     * @param lclDateTime date input in LocalDateTime datatype
     * @return a formatted date in String format for display
     */
    public static String convertDateToDisplay(LocalDateTime lclDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(displayFormat);
        return lclDateTime.format(df);
    }

    /**
     * isDateEqual method compare 2 LocalDateTime to check if it is equals
     * @param firstDate date input in LocalDateTime datatype to be compared
     * @param secondDate date input in LocalDateTime datatype to be compared
     * @return TRUE if date is equal; FALSE if date is equal
     */
    public static boolean isDateEqual(LocalDateTime firstDate, LocalDateTime secondDate) {
        assert firstDate != null : "first date input cannot be null";
        assert secondDate != null : "second date input cannot be null";

        if(firstDate.equals(secondDate)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * isEventDateClash method compare 2 Event date to check if it clashes
     * only under 2 conditions will the events not clash
     * - newEvent endDate isBefore existingEvent startDate OR newEvent startDate isAfter existingEvent endDate
     * @param eventExisting event obj input that exists to be compared
     * @param eventNew event obj input that is to be added to be compared
     * @return TRUE if event clashs; FALSE if doesn't
     */
    public static boolean isEventDateClash(Event eventExisting, Event eventNew) {
        assert eventExisting != null : "first event obj input cannot be null";
        assert eventNew != null : "second event obj input cannot be null";

        if(eventNew.getEnd().isBefore(eventExisting.getStart()) || eventNew.getStart().isAfter(eventExisting.getEnd())) {
            return false;
        }
        else
        {
            return true;
        }

    }

}
