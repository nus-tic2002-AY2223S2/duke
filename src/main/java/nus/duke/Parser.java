package nus.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Parser class used to parse the user command and returns the appropriate command objects.
 */
public abstract class Parser {

    protected static String command = null;
    protected static String[] wordsInDescription = null;
    protected static String[] partsInDescription = null;


    public static void parseMarkCommand(Command command) {
        // *************************
        // level 5 ErrorHandle
        // *************************
        try {
            command.taskNumber = Integer.parseInt(wordsInDescription[1]);
        } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
            System.out.println("mark is a function key. Please indicate task number to be marked!");
        } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
            System.out.println("mark feature must contain a task number");
        }
    }

    public static void parseUnmarkCommand(Command command) {
        try {
            command.taskNumber = Integer.parseInt(wordsInDescription[1]);
        } catch (NumberFormatException nfe) {              // to handle exception: mark nonINTEGER
            System.out.println("unmark is a function key. Please indicate task number to be unmarked!");
        } catch (ArrayIndexOutOfBoundsException obe) {  // to handle exception: mark
            System.out.println("unmark feature must contain a task number");
        }
    }

    public static void parseTodoCommand(Command command) {
        String taskJob = "";
        try {
            DukeException.checkDescriptionExist(wordsInDescription.length, 2);  // handle exception: no Task
            // if there is to-do task: skip "to-do" and put description in taskArray
            for (int i = 1; i < wordsInDescription.length; i++) {
                taskJob += wordsInDescription[i] + " ";
            }
            command.taskDescription = taskJob;
        } catch (DukeException de) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void parseDeadlineDateTime(Command command) {
        // *************************
        // level 8 Dates and Times
        // *************************
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            command.taskDeadline = LocalDateTime.parse(partsInDescription[1].substring(1), formatter);
        } catch (DateTimeParseException dtpe) {
            System.out.println("write the deadline in yyyy-MM-dd HH:mm format");
        }
    }

    public static void parseDeadlineCommand(Command command) {
        String taskJob = new String();
        try {
            DukeException.checkDescriptionExist(wordsInDescription.length, 2); //handle exception: no deadline(/by)
            DukeException.checkPartsRequirement(partsInDescription.length, 2);
            // if there is deadline task: skip "deadline" and extract the task and by
            int indexBy = Arrays.asList(wordsInDescription).indexOf("/by");
            for (int i = 1; i < indexBy; i++) {
                taskJob += wordsInDescription[i] + " ";
            }
            DukeException.checkTaskExist(indexBy, 1);
            command.taskDescription = taskJob;
            parseDeadlineDateTime(command);
        } catch (ArrayIndexOutOfBoundsException obe) {
            System.out.println("A deadline command must provide a deadline date & time (/by)!");
        } catch (IncompleteDescriptionException ide) {
            System.out.println("A deadline must contain only 1 task and only 1 deadline date & time (/by)!");
        } catch (NoTaskException nte) {
            System.out.println("There is no Task .");
        } catch (DukeException de) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void parseEventDateTime(Command command) {
        // *************************
        // level 8 Dates and Times
        // *************************
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            command.start = LocalDateTime.parse(partsInDescription[1].substring(1, partsInDescription[1].length() - 1), formatter);
            command.end = LocalDateTime.parse(partsInDescription[2].substring(1), formatter);
        } catch (DateTimeParseException dtpe) {
            System.out.println("write the event date & time in yyyy-MM-dd HH:mm format");
        }
    }

    public static void parseEventCommand(Command command) {
        // *************************
        // level 4 Event feature
        // *************************
        String taskJob = "";
        int indexFrom = Arrays.asList(wordsInDescription).indexOf("/from");
        try {
            DukeException.checkDescriptionExist(wordsInDescription.length, 2); //handle exception: event only)
            DukeException.checkPartsRequirement(partsInDescription.length, 3);
            // if there is event duty: skip "event" and put the duty in taskArray
            for (int i = 1; i < indexFrom; i++) {
                taskJob += wordsInDescription[i] + " ";
            }
            DukeException.checkTaskExist(indexFrom, 1);
            command.taskDescription = taskJob;
            parseEventDateTime(command);
        } catch (ArrayIndexOutOfBoundsException obe) {
            System.out.println("An event must contain a start period (/from) and a end period (/to)!");
        } catch (IncompleteDescriptionException ide) {
            System.out.println("Events must contain 1 task, 1 /from and 1 /to");
        } catch (NoTaskException nte) {
            System.out.println("There is no Task .");
        } catch (DukeException de) {
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    // *************************
    // level 5 Delete feature
    // *************************
    public static void parseDeleteCommand(Command command) {
        try {
            command.taskNumber = Integer.parseInt(wordsInDescription[1]);
        } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
            System.out.println("delete is a function key. Please indicate task number to be deleted!");
        } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
            System.out.println("delete feature must contain a task number");
        }
    }

    // *************************
    // level 9 Find feature
    // *************************
    public static void parseFindCommand(Command command) {
        String findItem = new String();
        try {
            DukeException.checkDescriptionExist(wordsInDescription.length, 2);  // handle exception: no word
            // if there is find task: skip "find" and put description in taskArray
            for (int i = 1; i < wordsInDescription.length; i++) {
                findItem += wordsInDescription[i] + " ";
            }
            command.findItem = findItem;
        } catch (DukeException de) {
            System.out.println("☹ OOPS!!! The description of a FIND cannot be empty.");
        }
    }

    // *************************
    // level 6 ENUM
    // *************************
    public enum taskEnum {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, FIND, STAT
    }

    /**
     * Parses the user input(fullCommand) and returns the appropriate command object.
     * @param fullCommand the user input string
     * @return the command object corresponding to the user input
     * @throws DukeException if the user input is not a valid command
     * @throws InvalidCodeException if the relevant attribute equal to null
     */

    public static Command parse(String fullCommand) throws DukeException, InvalidCodeException {

        Parser.command = fullCommand;
        Command c = new Command();
        Parser.wordsInDescription = fullCommand.split(" ");
        Parser.partsInDescription = fullCommand.split("/by|/from|/to");

        //initialized an object for Enum task
        Parser.taskEnum objectEnumTask = null;
        try {
            objectEnumTask = Parser.taskEnum.valueOf(String.valueOf(taskEnum.valueOf(wordsInDescription[0].toUpperCase())));   // convert different types of values into string
        } catch (IllegalArgumentException iae) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (objectEnumTask == null) {
            throw new InvalidCodeException("Invalid code exception");
        }

        c.task = objectEnumTask;

            switch (objectEnumTask) {
                case BYE:
                    break;

                case MARK:
                    parseMarkCommand(c);
                    break;

                case UNMARK:
                    parseUnmarkCommand(c);
                    break;

                case TODO:
                    parseTodoCommand(c);
                    break;

                case DEADLINE:
                    parseDeadlineCommand(c);
                    break;

                case EVENT:
                    parseEventCommand(c);
                    break;

                case DELETE:
                    parseDeleteCommand(c);
                    break;

                case FIND:
                    parseFindCommand(c);
                    break;

                case STAT:
                    break;

                default:
            }
        return c;
    }
}

