package duke.command;

import duke.exception.IllegalDeadlineException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalTaskException;
import duke.exception.IllegalTodoException;
import duke.parser.Parser;
import duke.storage.DukeFileWriter;
import duke.task.Task;
import duke.task.TaskList;
import duke.type.CommandType;
import duke.type.TaskType;
import duke.ui.Ui;

import static duke.parser.Parser.*;

public class Command {
    CommandType commandType;
    String command;

    boolean isExit;

    public Command(CommandType commandType, String command) {
        this.commandType = commandType;
        this.command = command;
        this.isExit = false;
    }

    public void execute(TaskList tasks){
        switch (commandType) {
            case EXIT:
                //Exit program
                isExit = true;
                Ui.exitCommand();
                break;
            case LIST:
                //Print list
                Ui.printList(tasks);
                break;
            case MARK:
            case UNMARK:
                //Mark or Unmark duke.task.Task
                Task task = Parser.getMarkTask(tasks, command, commandType);
                markTask(tasks, task);
                break;
            case DELETE:
                //Delete duke.task.Task
                deleteTask(tasks, command);
                break;
            case ADD:
                //Create and add task to list
                addTask(tasks, command);
                break;
            default:
                break;
        }
    }

    private void addTask(TaskList taskList, String command) {
        Task task = getTask(command);
        if (task != null) {
            taskList.addItem(task);

            String addedTask = Ui.getNewTaskString(taskList, task);
            Ui.printCommand(addedTask);

            DukeFileWriter.appendInFile(command + "@" + task.isDone() + System.lineSeparator());
        }
    }

    private static void deleteTask(TaskList taskList, String command) {
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

    private static void markTask(TaskList taskList, Task task){
        if (task != null) {
            String printStr = "";
            if (task.isDone()) {
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
    }

    public static Task getTask(String command) {
        Task task = null;
        try {
            TaskType taskType = Parser.getTaskType(command);
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

    public boolean isExit() {
        return isExit;
    }
}
