package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    /**
     * This method parse the user command
     *
     * @param command A string representing user command.
     * @return A Command object representing user input.
     */
    public static Command parse(String command) {
        CommandType commandType = getCommandType(command);
        Command c = new Command(commandType, command);
        return c;
    }


    /**
     * This method parse the user TODO command and create Todo object
     *
     * @param command A string representing the user command.
     * @return A Todo object parsed from the user input.
     */
    public static Todo parseTodo(String command) throws IndexOutOfBoundsException, IllegalTodoException, NumberFormatException {
        String[] commandStr = command.split(" ");
        if (commandStr.length < 2) {
            throw new IllegalTodoException();
        }

        String[] taskStr = commandStr[1].split("@");

        String description = taskStr[0].trim();
        Todo todo = new Todo(description);

        if (taskStr.length > 1) {
            String markAsDone = taskStr[1].trim();
            boolean mark = Boolean.parseBoolean(markAsDone);
            if (mark) {
                todo.markAsDone();
            }
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
        String[] deadlines = command.split("/by");
        if (deadlines.length < 2) {
            throw new IllegalDeadlineException();
        }
        String[] commandStr = deadlines[0].split(" ");
        if (commandStr.length < 2) {
            throw new IllegalDeadlineException();
        }
        String description = deadlines[0].replaceFirst(commandStr[0], "").trim();

        String[] byStr = deadlines[1].split("@");
        String by = formatDateTime(byStr[0].trim());
        Deadline deadline = new Deadline(description, by);

        if (byStr.length > 1) {
            String markAsDone = byStr[1].trim();
            boolean mark = Boolean.parseBoolean(markAsDone);
            if (mark) {
                deadline.markAsDone();
            }
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
        String[] events = command.split("/from");
        if (events.length < 2) {
            throw new IllegalEventException();
        }
        String[] commandStr = events[0].split(" ");
        if (commandStr.length < 2) {
            throw new IllegalEventException();
        }
        String description = events[0].replaceFirst(commandStr[0], "").trim();

        String[] dates = events[1].split("/to");
        if (dates.length < 2) {
            throw new IllegalEventException();
        }
        String fromDate = formatDateTime(dates[0].trim());

        String[] toStr = dates[1].split("@");
        String toDate = formatDateTime(toStr[0].trim());

        Event event = new Event(description, fromDate, toDate);

        if (toStr.length > 1) {
            String markAsDone = toStr[1].trim();
            boolean mark = Boolean.parseBoolean(markAsDone);
            if (mark) {
                event.markAsDone();
            }
        }

        return event;
    }


    /**
     * This method parse the user Mark/Unmark command and modify Task object
     *
     * @param taskList A TaskList object representing list of tasks.
     * @param command A string representing the user command.
     * @param commandType A string representing the type of user command.
     * @return A modified Task object based on user input.
     */
    public static Task getMarkTask(TaskList taskList, String command, CommandType commandType) {
        command = command.toUpperCase();
        Task task = null;
        boolean markAsDone = commandType == CommandType.MARK ? true : false;
        try{
            String numberStr = command.split(" ")[1].trim();
            int index = Integer.parseInt(numberStr) - 1;
            task = taskList.getItem(index);
            if (markAsDone) {
                task.markAsDone();
            }else {
                task.unMarkDone();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException : " + command);
        }
        catch (NumberFormatException ex) {
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
        }else if (command.toUpperCase().startsWith("DEADLINE")) {
            return TaskType.DEADLINE;
        }else if (command.toUpperCase().startsWith("EVENT")) {
            return TaskType.EVENT;
        }else {
            return TaskType.UNKNOWN;
        }
    }

    /**
     * This method parse user command and returns the type of command
     *
     * @param command A string representing the user command.
     * @return A CommandType representing the type of Command
     */
    public static CommandType getCommandType(String command) {
        if (command.isEmpty()){
            return CommandType.EMPTY;
        }else if (command.equalsIgnoreCase("BYE")) {
            return CommandType.EXIT;
        }else if (command.equalsIgnoreCase("LIST")){
            return CommandType.LIST;
        }else if (command.toUpperCase().startsWith("MARK")) {
            return CommandType.MARK;
        }else if (command.toUpperCase().startsWith("UNMARK")) {
            return CommandType.UNMARK;
        }else if (command.toUpperCase().startsWith("DELETE")) {
            return CommandType.DELETE;
        }else {
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
        }catch (IllegalTodoException e){
            Ui.printCommand("☹ OOPS!!! The description of a todo cannot be empty.");
        }catch (IllegalDeadlineException e){
            Ui.printCommand("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        }catch (IllegalEventException e){
            Ui.printCommand("☹ OOPS!!! The description or start date or end date of an event cannot be empty.");
        }catch (IllegalTaskException e){
            Ui.printCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }catch (IndexOutOfBoundsException e){
            System.out.println("IndexOutOfBoundsException for task: " + command);
        }
        return task;
    }

    /**
     * This method format valid date/datetime string into another format.
     *
     * @param datetime A string representing the date.
     * @return A datetime String in the format 'MMM d yyyy haa' if it's valid date input.
     */
    public static String formatDateTime(String datetime) {
        String formattedDate = datetime;
        try {
            LocalDate date = LocalDate.parse(datetime);
            formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy haa"));
        } catch (DateTimeParseException e) {
            Ui.printCommand("☹ OOPS!!! Invalid DateTime format (DateTimeParseException) --> " + datetime);
        }
        return formattedDate;
    }

}
