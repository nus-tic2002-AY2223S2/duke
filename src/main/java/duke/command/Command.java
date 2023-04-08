package duke.command;

import duke.storage.DukeFileReaderAndWriter;
import duke.task.Task;
import duke.task.TaskList;
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
        this.isExit = commandType == CommandType.EXIT;
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
    public void execute(TaskList tasks, DukeFileReaderAndWriter fileReaderAndWriter) {
        try {
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
                    //Mark or Unmark task
                    markTask(tasks, commandType, fileReaderAndWriter);
                    break;
                case DELETE:
                    //Delete a task
                    deleteTask(tasks, command, fileReaderAndWriter);
                    break;
                case ADD:
                    //Create and add task to list
                    addTask(tasks, command, fileReaderAndWriter);
                    break;
                case EDIT:
                    //Edit task in the list
                    editTask(tasks, command, fileReaderAndWriter);
                    break;
                case CLONE:
                    //Clone an existing task
                    cloneTask(tasks, command, fileReaderAndWriter);
                    break;
                case FIND:
                    //Find a task by searching for a keyword.
                    findTask(tasks, command);
                    break;
            }
        } catch (IndexOutOfBoundsException ex) {
            Ui.printException("IndexOutOfBoundsException : " + command);
        } catch (NumberFormatException ex) {
            Ui.printException("NumberFormatException for task: " + command);
        }
    }

    /**
     * This method find a task by searching for a keyword.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command. Edit command should be in the format "find [keyword]"
     */
    private void findTask(TaskList taskList, String command) throws IndexOutOfBoundsException, NumberFormatException {
        String keyword = parseKeyword(command);
        TaskList tasks = taskList.getFilteredItems(keyword);

        Ui.printList(tasks);
    }

    /**
     * This method clone an existing task
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command. Clone command should be in the format "clone [index]"
     */
    private void cloneTask(TaskList taskList, String command, DukeFileReaderAndWriter fileReaderAndWriter) throws IndexOutOfBoundsException, NumberFormatException {
        int index = parseIndex(command);
        Task task = taskList.getItem(index);

        taskList.addItem(task);

        String clonedLine = fileReaderAndWriter.readLine(index);
        fileReaderAndWriter.appendInFile(clonedLine + System.lineSeparator());

        Ui.printNewTaskString(taskList, task);
    }

    /**
     * This method edit task from users list of tasks.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command. Edit command should be in the format "edit [index] [/description || /by || /to || /from] update-text"
     */
    private void editTask(TaskList taskList, String command, DukeFileReaderAndWriter fileReaderAndWriter) throws IndexOutOfBoundsException, NumberFormatException {
        int index = parseIndex(command);
        Task task = taskList.getItem(index);

        if (!checkValidEditCommand(command, task.getType())) {
            Ui.printCommand("Invalid edit command : " + command);
            return;
        }

        parseEditTask(command, task);
        Ui.printEditedTaskString(task);

        String taskStr = taskList.getTaskString(index);

        fileReaderAndWriter.editFile(index, taskStr);
    }

    /**
     * This method add task into users list of tasks.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command.
     */
    private void addTask(TaskList taskList, String command, DukeFileReaderAndWriter fileReaderAndWriter) {
        Task task = getTask(command);
        if (task != null) {
            taskList.addItem(task);

            fileReaderAndWriter.appendInFile(command + IS_EXIT_SEPARATOR + task.isDone() + System.lineSeparator());

            Ui.printNewTaskString(taskList, task);
        }
    }

    /**
     * This method delete task from users list of tasks.
     *
     * @param taskList A TaskList object representing list of tasks of the user.
     * @param command  A string representing user command.
     */
    private void deleteTask(TaskList taskList, String command, DukeFileReaderAndWriter fileReaderAndWriter) throws IndexOutOfBoundsException, NumberFormatException {
        int index = parseIndex(command);
        Task task = taskList.removeItem(index);

        String taskStr = taskList.getTaskListString();
        fileReaderAndWriter.writeInFile(taskStr);

        Ui.printDeletedTaskString(taskList, task);
    }

    /**
     * This method mark task as done or not done.
     *
     * @param taskList    A TaskList object representing list of tasks of the user.
     * @param commandType A string representing the type of user command.
     */
    private void markTask(TaskList taskList, CommandType commandType, DukeFileReaderAndWriter fileReaderAndWriter) throws IndexOutOfBoundsException, NumberFormatException {
        int index = parseIndex(command);
        Task task = taskList.getItem(index);

        String printStr = "";
        boolean isMarkAsDone = commandType == CommandType.MARK;
        if (isMarkAsDone) {
            task.markAsDone();
            printStr = "Nice! I've marked this task as done:";
        } else {
            task.unMarkDone();
            printStr = "OK, I've marked this task as not done yet:";
        }

        fileReaderAndWriter.writeInFile(taskList.getTaskListString());

        Ui.printCommand(printStr + System.lineSeparator() + "\t" + task);
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
