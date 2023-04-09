package duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateValidator {

    public static String dateFormat = "yyyy-MM-dd HH-mm-ss";

    public DateValidator() {

    }

    public static boolean validateInput(String dateString) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static LocalDate convertStringToDate(String dateString) throws DukeException {
        if(validateInput(dateString))
        {
        LocalDate dateFormatted = LocalDate.parse(dateString);
            return dateFormatted;
        }else {
            throw new DukeException("Date Format Error");
        }
    }

    public static boolean isDateEqual(LocalDate firstDate, LocalDate secondDate) {
        if(firstDate.equals(secondDate)) {
            return true;
        }
        else {
            return false;
        }
    }

}
