package duke.parser;

import duke.command.Command;
import duke.exception.IllegalDeadlineException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalTaskException;
import duke.exception.IllegalTodoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.type.CommandType;
import duke.type.TaskType;
import duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A <code>duke.parser.Parser</code> class deals with making sense of the user command
 */
public class Parser {

    public static final String DESCRIPTION_COMMAND = "/description";
    public static final String BY_COMMAND = "/by";
    public static final String FROM_COMMAND = "/from";
    public static final String TO_COMMAND = "/to";

    public static final String IS_EXIT_SEPARATOR = "@";


    /**
     * This method parse the user command
     *
     * @param command A string representing user command.
     * @return A Command object representing user input.
     */
    public static Command parse(String command) {
        CommandType commandType = parseCommandType(command);
        Command commandObj = new Command(commandType, command);
        return commandObj;
    }

    /**
     * This method parse index from the user command
     *
     * @param command A string representing user command.
     * @return index of the task
     */
    public static int parseIndex(String command) throws IndexOutOfBoundsException, NumberFormatException {
        String numberStr = command.split(" ")[1].trim();
        int index = Integer.parseInt(numberStr) - 1;
        return index;
    }

    /**
     * This method parse keyword from the user command
     *
     * @param command A string representing user command.
     * @return extracted keyword
     */
    public static String parseKeyword(String command) throws IndexOutOfBoundsException {
        String keyword = command.split(" ")[1].trim();
        return keyword;
    }

    private static void parseAndMarkTask(String markString, Task task) {
        boolean isMarkAsDone = Boolean.parseBoolean(markString);
        if (isMarkAsDone) {
            task.markAsDone();
        }
    }

    /**
     * This method parse the user TODO command and create Todo object
     *
     * @param command A string representing the user command.
     * @return A Todo object parsed from the user input.
     */
    public static Todo parseTodo(String command) throws IndexOutOfBoundsException, IllegalTodoException, NumberFormatException {
        String[] commandStringList = command.split(" ");
        //assert commandStringList.length >= 2 : "☹ OOPS!!! The description of a todo cannot be empty.";
        if (commandStringList.length < 2) {
            throw new IllegalTodoException();
        }

        String[] taskStringList = commandStringList[1].split(IS_EXIT_SEPARATOR);
        String description = taskStringList[0].trim();

        Todo todo = new Todo(description);

        if (taskStringList.length > 1) {
            String markString = taskStringList[1].trim();
            parseAndMarkTask(markString, todo);
        }

        return todo;
    }

    /**
     * This method parse the user Deadline command and create Deadline object
     *
     * @param command A string representing the user command.
     * @return A Deadline object parsed from the user input.
     */
    public static Deadline parseDeadline(String command) throws IndexOutOfBoundsException, IllegalDeadlineException {
        String[] commandStringList = command.split(BY_COMMAND);
        if (commandStringList.length < 2) {
            throw new IllegalDeadlineException();
        }
        String[] taskStringList = commandStringList[0].split(" ");
        if (taskStringList.length < 2) {
            throw new IllegalDeadlineException();
        }
        String description = commandStringList[0].replaceFirst(taskStringList[0], "").trim();

        String[] deadlineStringList = commandStringList[1].split(IS_EXIT_SEPARATOR);
        String by = formatDateTime(deadlineStringList[0].trim());
        Deadline deadline = new Deadline(description, by);

        if (deadlineStringList.length > 1) {
            String markString = deadlineStringList[1].trim();
            parseAndMarkTask(markString, deadline);
        }

        return deadline;
    }

    /**
     * This method parse the user Event command and create Event object
     *
     * @param command A string representing the user command.
     * @return A Event object parsed from the user input.
     */
    public static Event parseEvent(String command) throws IndexOutOfBoundsException, IllegalEventException {
        String[] commandStringList = command.split(FROM_COMMAND);
        if (commandStringList.length < 2) {
            throw new IllegalEventException();
        }
        String[] taskStringList = commandStringList[0].split(" ");
        if (taskStringList.length < 2) {
            throw new IllegalEventException();
        }

        String description = commandStringList[0].replaceFirst(taskStringList[0], "").trim();

        String[] dates = commandStringList[1].split(TO_COMMAND);
        if (dates.length < 2) {
            throw new IllegalEventException();
        }
        String fromDate = formatDateTime(dates[0].trim());

        String[] toDateStringList = dates[1].split(IS_EXIT_SEPARATOR);
        String toDate = formatDateTime(toDateStringList[0].trim());

        Event event = new Event(description, fromDate, toDate);

        if (toDateStringList.length > 1) {
            String markString = toDateStringList[1].trim();
            parseAndMarkTask(markString, event);
        }

        return event;
    }


    /**
     * This method parse user command and returns the type of task
     *
     * @param command A string representing the user command.
     * @return A TaskType representing the type of Task
     */
    public static TaskType getTaskType(String command) {
        if (command.toUpperCase().startsWith("TODO")) {
            return TaskType.TODO;
        } else if (command.toUpperCase().startsWith("DEADLINE")) {
            return TaskType.DEADLINE;
        } else if (command.toUpperCase().startsWith("EVENT")) {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }

    /**
     * This method parse user command and returns the type of command
     *
     * @param command A string representing the user command.
     * @return A CommandType representing the type of Command
     */
    public static CommandType parseCommandType(String command) {
        if (command.isEmpty()) {
            return CommandType.EMPTY;
        } else if (command.equalsIgnoreCase("BYE")) {
            return CommandType.EXIT;
        } else if (command.equalsIgnoreCase("LIST")) {
            return CommandType.LIST;
        } else if (command.toUpperCase().startsWith("MARK")) {
            return CommandType.MARK;
        } else if (command.toUpperCase().startsWith("UNMARK")) {
            return CommandType.UNMARK;
        } else if (command.toUpperCase().startsWith("DELETE")) {
            return CommandType.DELETE;
        } else if (command.toUpperCase().startsWith("EDIT")) {
            return CommandType.EDIT;
        } else if (command.toUpperCase().startsWith("CLONE")) {
            return CommandType.CLONE;
        } else if (command.toUpperCase().startsWith("FIND")) {
            return CommandType.FIND;
        } else {
            return CommandType.ADD;
        }
    }


    /**
     * This method parse user command, creates and returns task
     *
     * @param command A string representing the user command.
     * @return A Task object created based on user command.
     */
    public static Task getTask(String command) {
        Task task = null;
        try {
            TaskType taskType = getTaskType(command);
            switch (taskType) {
                case TODO:
                    task = parseTodo(command);
                    break;
                case DEADLINE:
                    task = parseDeadline(command);
                    break;
                case EVENT:
                    task = parseEvent(command);
                    break;
                default:
                    throw new IllegalTaskException();
            }
        } catch (IllegalTodoException e) {
            Ui.printCommand("☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (IllegalDeadlineException e) {
            Ui.printCommand("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        } catch (IllegalEventException e) {
            Ui.printCommand("☹ OOPS!!! The description or start date or end date of an event cannot be empty.");
        } catch (IllegalTaskException e) {
            Ui.printCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            Ui.printCommand("IndexOutOfBoundsException for task: " + command);
        }
        return task;
    }

    /**
     * This method check if the command is a valid edit command
     *
     * @param command  A string representing the user command.
     * @param taskType Type of task to be edited.
     * @return A boolean indicating whether the edit command is valid or not.
     */
    public static boolean checkValidEditCommand(String command, TaskType taskType) {
        boolean isValid = false;
        if (command.contains(DESCRIPTION_COMMAND)) {
            isValid = true;
        } else if (command.contains(BY_COMMAND) && taskType == TaskType.DEADLINE) {
            isValid = true;
        } else if ((command.contains(FROM_COMMAND) || command.contains(TO_COMMAND)) && taskType == TaskType.EVENT) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * This method parse edit command and edit the task
     *
     * @param command A string representing the user command.
     */
    public static void parseEditTask(String command, Task task) throws IndexOutOfBoundsException {
        if (command.contains(DESCRIPTION_COMMAND)) {
            String[] taskElements = command.split(DESCRIPTION_COMMAND);
            String description = taskElements[taskElements.length - 1].trim();
            task.setDescription(description);
        } else if (command.contains(BY_COMMAND)) {
            String[] taskElements = command.split(BY_COMMAND);
            String by = formatDateTime(taskElements[taskElements.length - 1].trim());
            Deadline deadline = (Deadline) task;
            deadline.setBy(by);
        } else if (command.contains(FROM_COMMAND)) {
            Event event = (Event) task;
            String[] taskElements = command.split(FROM_COMMAND);
            String from = formatDateTime(taskElements[taskElements.length - 1].trim());
            event.setEndDateTime(from);
        } else if (command.contains(TO_COMMAND)) {
            Event event = (Event) task;
            String[] taskElements = command.split(TO_COMMAND);
            String to = formatDateTime(taskElements[taskElements.length - 1].trim());
            event.setStartDateTime(to);
        }
    }

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
            Ui.printCommand("☹ OOPS!!! Invalid DateTime format (DateTimeParseException) --> " + datetime);
        }

        return formattedDate;
    }
}
