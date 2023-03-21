package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.DateFormat;
import java.text.ParseException;
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
        String formatString;
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
                if (!userInput.matches(".*/by.*")){
                    throw new DukeException("Please make sure the sequence is correct /by, please try again");
                }
                if (formatDeadlineSplit.length < 2) {
                    throw new DukeException("deadline is missing /by details, please try again");
                } else if (formatDeadlineSplit.length > 2) {
                    throw new DukeException("deadline has too many parameters, please try again");
                } else {
                    formatDeadlineSplit[1] = formatDeadlineSplit[1].replace("by", "").trim();
                    return formatDeadlineSplit;
                }

            case "event":
                formatString = userInput.replace("event", "").trim();
                String[] formatEventSplit = formatString.split("/from | /to");
                if (!userInput.matches(".*from.*to.*")){
                    throw new DukeException("Please make sure the sequence is correct /from /to, please try again");
                }
                if (formatEventSplit.length < 3) {
                    throw new DukeException("duke.task.Event is missing either from or to, please try again");
                } else if (formatEventSplit.length > 3) {
                    throw new DukeException("duke.task.Event has too many parameters, please try again");
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
     * This 8 command method check if the user input starts with the list of commands
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

    public boolean isFindCommand(String userInput) {
        return userInput.startsWith("find");
    }

    /**
     * The createToDo method creates a To Do task based on the user input.
     * It extracts the description of the task from the user input and creates a new To Do task with that description.
     *
     * @param userInput the user input to be parsed.
     * @return the new To Do task that was created.
     * @throws DukeException if the description of the To Do task is empty.
     */
    public Task createToDo(String userInput) throws DukeException {
        String[] stringSplit = new String[1];
        stringSplit[0] = userInput.replace("todo", "").trim();
        if (stringSplit[0].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new ToDo(stringSplit[0], "T");
        }
    }

    /**
     * The createDeadline method creates a duke.task.Deadline task based on the user input.
     *
     * @param userInput the user input to be parsed.
     * @return the new duke.task.Deadline task that was created.
     * @throws DukeException if the description of the duke.task.Deadline task is empty.
     */
    public Task createDeadline(String userInput) throws DukeException {
        String[] formattedString = formatString(userInput, "deadline");
        String description = formattedString[0];

        String by = convertToDateTime(formattedString[1]);

        return new Deadline(description, "D", by);
    }

    /**
     * The createEvent method creates a duke.task.Event task based on the user input.
     *
     * @param userInput the user input to be parsed.
     * @return the new duke.task.Event task that was created.
     * @throws DukeException if the description of the duke.task.Event task is empty.
     */
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
    public String convertToDateTime(String datetime) throws DukeException {

        DateFormat df = new SimpleDateFormat("d/M/yyyy HHmm");
        DateFormat outputformat = new SimpleDateFormat("dd MMM yyyy haa");

        String output;
        Date date;
        try {
            date = df.parse(datetime);
            output = outputformat.format(date);
        } catch (ParseException e) {
            throw new DukeException("Invalid DateTime format, Please follow this format: d/M/yyyy HHmm");
        }
        return output;
    }

}
