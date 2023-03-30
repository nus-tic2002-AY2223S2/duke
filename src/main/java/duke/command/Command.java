package duke.command;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.type.CommandType;
import duke.ui.Ui;

import static duke.parser.Parser.*;

public class Command {
    CommandType commandType;
    String command;

    boolean isExit;

    public Command(CommandType commandType, String command) {
        this.commandType = commandType;
        this.command = command;
        if (commandType == CommandType.EXIT) {
            this.isExit = true;
        } else {
            this.isExit = false;
        }
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getCommand() {
        return command;
    }

    /**
     * This method execute the user command
     *
     * @param tasks A TaskList object representing list of tasks of the user.
     */
    public void execute(TaskList tasks) {
        switch (commandType) {
            case EXIT:
                //Exit program
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
            case EDIT:
                //Edit task in the list
                editTask(tasks, command);
                break;
        }
    }


    /**
     * This method edit task from users list of tasks.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command.
     */
    private void editTask(TaskList taskList, String command) {
        try {
            int index = parseIndex(command);
            Task task = taskList.getItem(index);

            parseEditTask(command, task);
            Ui.printEditedTaskString(task);

            String taskStr = taskList.getTaskString(index);
            Storage.editFile(index, taskStr);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException : " + command);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method add task into users list of tasks.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command.
     */
    private void addTask(TaskList taskList, String command) {
        Task task = getTask(command);
        if (task != null) {
            taskList.addItem(task);

            Storage.appendInFile(command + "@" + task.isDone() + System.lineSeparator());

            Ui.printNewTaskString(taskList, task);
        }
    }

    /**
     * This method delete task from users list of tasks.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command.
     */
    private void deleteTask(TaskList taskList, String command) {
        try {
            int index = parseIndex(command);
            Task task = taskList.removeItem(index);

            String taskStr = taskList.getTaskListString();
            Storage.writeInFile(taskStr);

            Ui.printDeletedTaskString(taskList, task);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException for task: " + command);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method mark task as done or not done.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param task     A Task object representing task that needs to marked as done or not done.
     */
    private void markTask(TaskList taskList, Task task) {
        if (task != null) {
            String printStr = "";
            if (task.isDone()) {
                task.markAsDone();
                printStr = "Nice! I've marked this task as done:";
            } else {
                task.unMarkDone();
                printStr = "OK, I've marked this task as not done yet:";
            }

            String taskStr = taskList.getTaskListString();
            Storage.writeInFile(taskStr);

            Ui.printCommand(printStr + System.lineSeparator() + "\t" + task);
        }
    }

    /**
     * This method returns if it's an exit command.
     *
     * @return A boolean representing whether to exit the program or not.
     */
    public boolean isExit() {
        return isExit;
    }
}
