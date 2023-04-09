package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    /**
     * This method format valid date/datetime string into another format.
     *
     * @param datetime A string representing the date.
     * @return A datetime String in the format 'MMM d yyyy' if it's valid date input.
     */
    public static String formatDateTime(String datetime) {
        String formattedDate = datetime;
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("MMM d yyyy");

        try {
            formattedDate = myFormat.format(fromUser.parse(datetime));
        } catch (ParseException e) {
            //No need to print error - only need to handle those supported date format or accept default value
        }

        return formattedDate;
    }

//    public static <T> ArrayList<T> listToArrayList(List<T> list) {
//        return list != null ? new ArrayList<>(list) : null;
//    }

}


