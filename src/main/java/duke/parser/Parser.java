package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import duke.command.Command;
import duke.exception.IllegalTaskException;
import duke.task.TaskList;
import duke.type.TaskType;
import duke.exception.IllegalDeadlineException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalTodoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.type.CommandType;
import duke.ui.Ui;

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

    private static void parseAndMarkTask(String markString, Task task) {
        boolean markAsDone = Boolean.parseBoolean(markString);
        if (markAsDone) {
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
     * This method parse the user Mark/Unmark command and modify Task object
     *
     * @param taskList    A TaskList object representing list of tasks.
     * @param command     A string representing the user command.
     * @param commandType A string representing the type of user command.
     * @return A modified Task object based on user input.
     */
    public static Task getMarkTask(TaskList taskList, String command, CommandType commandType) {
        command = command.toUpperCase();
        Task task = null;
        boolean markAsDone = commandType == CommandType.MARK ? true : false;

        try {
            int index = parseIndex(command);
            task = taskList.getItem(index);
            if (markAsDone) {
                task.markAsDone();
            } else {
                task.unMarkDone();
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException : " + command);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return task;
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
            System.out.println("IndexOutOfBoundsException for task: " + command);
        }
        return task;
    }


    /**
     * This method parse edit command and edit the task
     *
     * @param command A string representing the user command.
     */
    public static void parseEditTask(String command, Task task) {
        try {

            if (command.contains(DESCRIPTION_COMMAND)) {
                String taskElements[] = command.split(DESCRIPTION_COMMAND);
                String description = taskElements[taskElements.length - 1].trim();
                task.setDescription(description);
            }

            TaskType taskType = task.getType();
            switch (taskType) {
                case DEADLINE:
                    if (command.contains(BY_COMMAND)) {
                        String taskElements[] = command.split(BY_COMMAND);
                        String by = formatDateTime(taskElements[taskElements.length - 1].trim());
                        Deadline deadline = (Deadline) task;
                        deadline.setBy(by);
                    }
                    break;
                case EVENT:
                    Event event = (Event) task;
                    if (command.contains(FROM_COMMAND)) {
                        String taskElements[] = command.split(FROM_COMMAND);
                        String from = formatDateTime(taskElements[taskElements.length - 1].trim());
                        event.setEndDateTime(from);
                    } else if (command.contains(TO_COMMAND)) {
                        String taskElements[] = command.split(TO_COMMAND);
                        String to = formatDateTime(taskElements[taskElements.length - 1].trim());
                        event.setStartDateTime(to);
                    }
                    break;
                default:
                    break;
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException : " + command);
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
