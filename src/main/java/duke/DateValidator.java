package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    private static String dateFormat = "yyyy-MM-dd HH:mm";
    private static String displayFormat = "dd MMM yyyy HH:mm";

    public DateValidator() {

    }

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
    public static String convertDateToString(LocalDateTime lclDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        return lclDateTime.format(df);
    }

    public static String convertDateToDisplay(LocalDateTime lclDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(displayFormat);
        return lclDateTime.format(df);
    }


    public static boolean isDateEqual(LocalDateTime firstDate, LocalDateTime secondDate) {
        if(firstDate.equals(secondDate)) {
            return true;
        }
        else {
            return false;
        }
    }

}
