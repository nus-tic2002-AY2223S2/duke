package duke.parser;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.IllegalDeadlineException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalTaskException;
import duke.exception.IllegalTodoException;
import duke.storage.DukeFileWriter;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.type.CommandType;
import duke.type.TaskType;

/**
 * A <code>duke.parser.Parser</code> class deals with making sense of the user command
 */
public class Parser {
    private static TaskType getTaskType(String command) {
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

    public static Todo getTodo(String command) throws IndexOutOfBoundsException, IllegalTodoException, NumberFormatException {
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

    public static Deadline getDeadline(String command) throws IndexOutOfBoundsException, IllegalDeadlineException {
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
        String by = byStr[0].trim();
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

    public static Event getEvent(String command) throws IndexOutOfBoundsException, IllegalEventException {
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
        String fromDate = dates[0].trim();

        String[] toStr = dates[1].split("@");
        String toDate = toStr[0].trim();

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

    public static Task getTask(String command) {
        Task task = null;
        try {
            TaskType taskType = getTaskType(command);
            switch (taskType) {
                case TODO:
                    task = getTodo(command);
                    break;
                case DEADLINE:
                    task = getDeadline(command);
                    break;
                case EVENT:
                    task = getEvent(command);
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

    public static void addTask(TaskList taskList, String command) {
        Task task = getTask(command);
        if (task != null) {
            taskList.addItem(task);
            String addedTask = Ui.getNewTaskString(taskList, task);
            Ui.printCommand(addedTask);

            DukeFileWriter.appendInFile(command + "@" + task.isDone() + System.lineSeparator());
        }
    }

    public static void markTask(TaskList taskList, String command, CommandType commandType) {
        command = command.toUpperCase();
        boolean markAsDone = commandType == CommandType.MARK ? true : false;
        try{
            String numberStr = command.split(" ")[1].trim();
            int index = Integer.parseInt(numberStr) - 1;
            Task task = taskList.getItem(index);
            String printStr = "";
            if (markAsDone) {
                task.markAsDone();
                printStr = "Nice! I've marked this task as done:";
            }else {
                task.unMarkDone();
                printStr = "OK, I've marked this task as not done yet:";
            }

            String taskStr = getTaskList(taskList);
            DukeFileWriter.writeInFile(taskStr);

            Ui.printCommand(printStr + System.lineSeparator() + "\t" + task);
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException : " + command);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteTask(TaskList taskList, String command) {
        try{
            String numberStr = command.split(" ")[1].trim();
            int index = Integer.parseInt(numberStr) - 1;
            Task task = taskList.getItem(index);
            taskList.removeItem(index);
            String deletedTask = Ui.getDeletedTaskString(taskList, task);
            Ui.printCommand(deletedTask);

            String taskStr = getTaskList(taskList);
            DukeFileWriter.writeInFile(taskStr);
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException for task: " + command);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private static String getTaskList(TaskList taskList) {
        String printStr = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            printStr +=  taskList.getItem(i).toCommand() + "@" + taskList.getItem(i).isDone() + System.lineSeparator();
        }
        return printStr;
    }

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
}
