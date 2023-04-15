package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    /**
     * This method format valid date/datetime string into another format.
     *
     * @param datetime A string representing the date.
     * @return A datetime String in the format 'MMM dd yyyy' if it's valid date input.
     */
    public static String formatDateTime(String datetime) throws ParseException {
        String tempDate = datetime.replace("/", "-").replace(" ", "-");
        String formatFromDate = getDateFormat(tempDate);

        SimpleDateFormat userFormat = new SimpleDateFormat(formatFromDate);
        SimpleDateFormat myFormat = new SimpleDateFormat("MMM dd yyyy");

        String formattedDate = myFormat.format(userFormat.parse(tempDate));

        if (isAlpha(tempDate)) {
            formattedDate = tempDate;
        }

        return formattedDate;
    }

    public static String getDateFormat(String inputDate) {
        String dateFormat;

        if (inputDate.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
            dateFormat = "dd-MM-yyyy";
        } else if (inputDate.matches("([1-9])-([0-9]{2})-([0-9]{4})")) {
            dateFormat = "d-MM-yyyy";
        } else if (inputDate.matches("([0-9]{2})-([1-9])-([0-9]{4})")) {
            dateFormat = "dd-M-yyyy";
        } else if (inputDate.matches("([1-9])-([1-9])-([0-9]{4})")) {
            dateFormat = "d-M-yyyy";
        } else if (inputDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
            dateFormat = "yyyy-MM-dd";
        } else if (inputDate.matches("([0-9]{2})-([a-zA-Z]{3})-([0-9]{4})")) {
            dateFormat = "dd-MMM-yyyy";
        } else if (inputDate.matches("([a-zA-Z]{3})-([0-9]{2})-([0-9]{4})")) {
            dateFormat = "MMM-dd-yyyy";
        } else if (inputDate.matches("([0-9]{4})-([a-zA-Z]{3})-([0-9]{2})")) {
            dateFormat = "yyyy-MMM-dd";
        } else if (inputDate.matches("([0-9]{4})-([0-9]{2})-([a-zA-Z]{3})")) {
            dateFormat = "yyyy-dd-MMM";
        } else if (inputDate.matches("([0-9])-([a-zA-Z]{3})-([0-9]{4})")) {
            dateFormat = "d-MMM-yyyy";
        } else if (inputDate.matches("([a-zA-Z]{3})-([0-9])-([0-9]{4})")) {
            dateFormat = "MMM-d-yyyy";
        } else if (inputDate.matches("([0-9]{4})-([a-zA-Z]{3})-([0-9])")) {
            dateFormat = "yyyy-MMM-d";
        } else if (inputDate.matches("([0-9]{4})-([0-9])-([a-zA-Z]{3})")) {
            dateFormat = "yyyy-d-MMM";
        } else if (isAlpha(inputDate) && inputDate.matches("[a-zA-Z]{3}")) {
            dateFormat = "EE";
        } else if (isAlpha(inputDate) && inputDate.matches("[a-zA-Z]+")) {
            dateFormat = "EEE";
        } else {
            return "";
        }

        return dateFormat;

    }

    public static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }


//    public static <T> ArrayList<T> listToArrayList(List<T> list) {
//        return list != null ? new ArrayList<>(list) : null;
//    }

}


