package formatDateTime;

import ui.Ui;
import command.*;
import exception.DukeException;
import parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * local date time format
 * -> re-format the date and time
 */
public class LocalDateTime {
    /**
     *
     * @param dateTime -> the task's date and time
     * @return
     */
    public static String formatDate(String dateTime) {
        String updatedDateTime = "";
        try {
            if (dateTime.contains("/")) {
                DateTimeFormatter formatting = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                java.time.LocalDateTime date = java.time.LocalDateTime.parse(dateTime, formatting);

                //get the date
                String[] numDate = dateTime.split("/");

                    switch (numDate[0]) {
                        case "1":
                        case "01":
                        case "21":
                        case "31":
                            DateTimeFormatter outPutOne = DateTimeFormatter.ofPattern("d'st' 'of' MMMM yyyy, ha", Locale.ENGLISH);
                            updatedDateTime = date.format(outPutOne);
                            break;
                        case "2":
                        case "02":
                        case "22":
                            DateTimeFormatter outPutTwo = DateTimeFormatter.ofPattern("d'nd' 'of' MMMM yyyy, ha", Locale.ENGLISH);
                            updatedDateTime = date.format(outPutTwo);
                            break;
                        case "3":
                        case "03":
                        case "23":
                            DateTimeFormatter outPutThree = DateTimeFormatter.ofPattern("d'rd' 'of' MMMM yyyy, ha", Locale.ENGLISH);
                            updatedDateTime = date.format(outPutThree);
                            break;
                        default:
                            DateTimeFormatter outPut = DateTimeFormatter.ofPattern("d'th' 'of' MMMM yyyy, ha", Locale.ENGLISH);
                            updatedDateTime = date.format(outPut);
                    }
            } else {
                updatedDateTime = dateTime;
            }
        } catch (DateTimeParseException e) {
            Ui.printMsg("Invalid date and time format");
        }
        return updatedDateTime;
    }

}
