import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    /**
     * This method formats the user input based on the given status of the task.
     *
     * @param userInput A string representing the user input.
     * @param status    A string representing the status of the task ("todo", "deadline" or "event").
     * @return A string array containing the formatted user input.
     * @throws DukeException If the input is invalid, an exception will be thrown with an appropriate error message.
     */
    public static String[] formatString(String userInput, String status) throws DukeException {
        String formatString = "";
        String[] stringSplit = new String[3];
        switch (status) {
            case "todo":
                stringSplit[0] = userInput.replace("todo", "").trim();
                if (stringSplit[0].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                formatString = userInput.replace("deadline", "").trim();
                String[] formatDeadlineSplit = formatString.split("/by");
                formatDeadlineSplit[0] = formatDeadlineSplit[0].trim();

                if (formatDeadlineSplit.length < 2) {
                    throw new DukeException("deadline is missing by details, please try again");
                } else if (formatDeadlineSplit.length > 2) {
                    throw new DukeException("deadline has too many parameters, please try again");
                } else {
                    formatDeadlineSplit[1] = formatDeadlineSplit[1].replace("by", "").trim();
                    return formatDeadlineSplit;
                }

            case "event":
                formatString = userInput.replace("event", "").trim();
                String[] formatEventSplit = formatString.split("/from | /to");
                if (formatEventSplit.length < 3) {
                    throw new DukeException("Event is missing either from or to, please try again");
                } else if (formatEventSplit.length > 3) {
                    throw new DukeException("Event has too many parameters, please try again");
                } else {
                    formatEventSplit[0] = formatEventSplit[0].trim();
                    formatEventSplit[1] = formatEventSplit[1].trim();
                    formatEventSplit[2] = formatEventSplit[2].trim();

                    return formatEventSplit;

                }
            default:
                break;
        }
        return stringSplit;
    }

    /**
     * This 7 command method check if the user input starts with the list of commands
     *
     * @param userInput A string representing the user input.
     * @return A boolean value indicating whether the user input is a command or not.
     */
    public boolean isListCommand(String userInput) {
        return userInput.equals("list");
    }

    public boolean isDeleteCommand(String userInput) {
        return userInput.startsWith("delete");
    }

    public boolean isMarkCommand(String userInput) {
        return userInput.startsWith("mark");
    }

    public boolean isUnmarkCommand(String userInput) {
        return userInput.startsWith("unmark");
    }

    public boolean isToDoCommand(String userInput) {
        return userInput.startsWith("todo");
    }

    public boolean isDeadlineCommand(String userInput) {
        return userInput.startsWith("deadline");
    }

    public boolean isEventCommand(String userInput) {
        return userInput.startsWith("event");
    }

    public Task createToDo(String userInput) throws DukeException {
        String[] stringSplit = new String[1];
        stringSplit[0] = userInput.replace("todo", "").trim();
        if (stringSplit[0].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new ToDo(stringSplit[0], "T");
        }
    }

    public Task createDeadline(String userInput) throws DukeException {
        String[] formattedString = formatString(userInput, "deadline");
        String description = formattedString[0];

        String by = convertToDateTime(formattedString[1]);

        return new Deadline(description, "D", by);
    }

    public Task createEvent(String userInput) throws DukeException {
        String[] formattedString = formatString(userInput, "event");
        String description = formattedString[0];

        String from = convertToDateTime(formattedString[1]);
        String to = convertToDateTime(formattedString[2]);
        return new Event(description, "E", from, to);
    }

    /**
     * This method converts a date string in the format "d/M/yyyy HHmm" to a more readable format of "dd MMM yyyy haa".
     *
     * @param datetime A string representing a date and time in the format "d/M/yyyy HHmm". e.g.(2/12/2023 2000)
     * @return A string representing the converted date and time in the format "dd MMM yyyy haa". e.g.(02 Dec 2023 8PM)
     */
    public String convertToDateTime(String datetime) {

        DateFormat df = new SimpleDateFormat("d/M/yyyy HHmm");
        DateFormat outputformat = new SimpleDateFormat("dd MMM yyyy haa");

        String output = null;
        Date date = null;
        try {
            date = df.parse(datetime);
            output = outputformat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
