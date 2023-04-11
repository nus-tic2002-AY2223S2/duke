package duke.Parser;

import duke.Exception.DukeException;
import duke.TasksType.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"; //for invalidDateTime()

    /**
     * This method validated the input given by the user
     * only the specified commands are allowed, exceptions will be thrown if anything word is parsed.
     * Using regex, validates the event and deadline inputs are correct with the required information.
     * Checks for missing priority level or index
     * @param input takes in the string input that was given by user
     * @param list takes in the ArrayList for checking the size of the list to valid indices
     * @throws DukeException is displayed according to the type of error input
     */
    public static void validateQuestion(String input, ArrayList<Task> list) throws DukeException {

        String[] splitted = input.split(" ", 2);
        if (!splitted[0].equalsIgnoreCase("bye") &&
                !splitted[0].equalsIgnoreCase("todo") &&
                !splitted[0].equalsIgnoreCase("deadline") &&
                !splitted[0].equalsIgnoreCase("event") &&
                !splitted[0].equalsIgnoreCase("list") &&
                !splitted[0].equalsIgnoreCase("mark") &&
                !splitted[0].equalsIgnoreCase("unmark") &&
                !splitted[0].equalsIgnoreCase("delete") &&
                !splitted[0].equalsIgnoreCase("priority") &&
                !splitted[0].equalsIgnoreCase("find") &&
                !splitted[0].equalsIgnoreCase("help")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\nType help to list out the commands");
        }
        if (splitted.length < 2 && !splitted[0].equalsIgnoreCase("list")) {
            throw new DukeException("☹ OOPS!!! The description/index of a " + splitted[0] + " cannot be empty.");
        }
        if (splitted[0].equalsIgnoreCase("mark") || splitted[0].equalsIgnoreCase("unmark") || splitted[0].equalsIgnoreCase("delete") ) {
            if ( Integer.parseInt(splitted[1]) > list.size() || Integer.parseInt(splitted[1]) == 0) {
                throw new DukeException("☹ OOPS!!! Index is not within the size of items in the list");
            }
        }
        if (input.toLowerCase().startsWith("event")) { //checks if event input is valid
            Pattern pattern = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String description = matcher.group(1).trim();
                String from = matcher.group(2).trim();
                String to = matcher.group(3).trim();

                if (description.isEmpty()) {
                    throw new DukeException("Missing description for event");
                    //System.out.println("Missing description for event");
                } else {
                    System.out.println("Description: " + description);
                    System.out.println("From: " + from);
                    System.out.println("To: " + to);
                }
            } else {
                throw new DukeException("Invalid input for event");
            }
        }

        if (input.toLowerCase().startsWith("deadline")) { //checks if deadline input is valid
            if (!input.contains(" /by ")) {
                throw new DukeException("☹ OOPS!!! The event command is missing /by");
            }
            Pattern pattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String description = matcher.group(1).trim();
                String by = matcher.group(2).trim();

                if (description.isEmpty()) {
                    System.out.println("Missing description for deadline");
                } else {
                    System.out.println("Description: " + description);
                    System.out.println("By: " + by);
                }
            } else {
                throw new DukeException("Invalid input for deadline");
            }
        }

        String[] splitCommand = input.split(" ", 2); // starts checking if priority input is valid
        String command = splitCommand[0];
        if (command.equals("priority")) {
            if (splitCommand.length < 2) {
                throw new DukeException("Please provide both an index and a priority level (high/medium/low).");
            }
            try {
                String[] priorityArgs = splitCommand[1].trim().split(" ");
                if (priorityArgs.length != 2) {
                    throw new DukeException("Please provide both an index and a priority level (high/medium/low).");
                }
                switch (priorityArgs[1].toLowerCase()) {
                    case "high":
                        break;
                    case "medium":
                        break;
                    case "low":
                        break;
                    default:
                        throw new DukeException("Invalid priority level. Please provide a valid priority level (high/medium/low).");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid index format. Please provide a valid index.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid input. Please provide an index and a priority level (high/medium/low).");
            }
        }
    }


    /**
     * This method checks if the index for priority change is within the list size
     * @param index takes in an integer of the index input by user
     * @param list takes in the list of task
     * @throws DukeException if the index is out of bound
     */
    public static void checkPriority(int index,  ArrayList<Task> list) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Index is out of bounds. Please enter a valid index.");
        }
    }

    /**
     * This method ensures that the dateTime format input by user is correct
     * @param input takes in the string input of dateTime
     * @return false if dateTime format is different from requirement
     */
    public static boolean isValidDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}