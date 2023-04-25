package duke.command;

import duke.util.TaskList;
import duke.ui.Ui;
import duke.util.Storage;
import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;

/**
 * Command class represents the commands issued by the user.
 */
public class Command {

    /**
     * An enumeration of possible command types.
     */
    public enum CommandType {
        BYE, LIST, MARK, DELETE, TODO, DEADLINE, EVENT, UNMARK, FIND, HELP, DUE
    }

    private final CommandType command;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final int index;


    /**
     * Constructs a new Command object with the given command type, end time
     * @param command the description of task
     * @param endTime the end time of task
     */
    public Command(CommandType command, LocalDateTime endTime) {
        this.command = command;
        this.description = null;
        this.startTime = null;
        this.endTime = endTime;
        this.index = -1;
    }


    /**
     * Constructs a new Command object with the given command type, description, start time, and end time.
     * @param command the command type
     * @param description the task description
     * @param startTime task start time
     * @param endTime task end time
     */
    public Command(CommandType command, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.command = command;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.index = -1;
    }


    /**
     * Constructs a new Command object with the given command type, description, and start time.
     * @param command the command type
     * @param description the description of task
     * @param startTime the start time of task
     */
    public Command(CommandType command, String description, LocalDateTime startTime) {
        this.command = command;
        this.description = description;
        this.startTime = startTime;
        this.endTime = null;
        this.index = -1;
    }


    /**
     *Constructs a new Command object with the given command type and description.
     * @param command the command type
     * @param description the description of task
     */
    public Command(CommandType command, String description) {
        this.command = command;
        this.description = description;
        this.startTime = null;
        this.endTime = null;
        this.index = -1;
    }


    /**
     * Constructs a new Command object with the given command type.
     * @param command the command type
     */
    public Command(CommandType command) {
        this.command = command;
        this.description = null;
        this.startTime = null;
        this.endTime = null;
        this.index = -1;
    }


    /**
     * Constructs a new Command object with the given command type and task index.
     * @param command the command type
     * @param index the task index
     */
    public Command(CommandType command, int index) {
        this.command = command;
        this.description = null;
        this.startTime = null;
        this.endTime = null;
        this.index = index;
    }


    /**
     * Returns true if the command is of type BYE, indicating that the program should exit.
     * @return true if the command is of type BYE, false otherwise
     */
    public boolean isExit() {
        return this.command.equals(CommandType.BYE);
    }


    /**
     * Executes the command on the given task list, UI, and storage objects.
     * @param tasks  TaskList object to execute the command on
     * @param ui ui the UI object to interact with the user
     * @param storage the storage object
     * @throws DukeException if any errors occur during the execution of the command
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.command) {
            case BYE:
                storage.save(tasks);
                System.out.println(ui.showBye());
                break;
            case LIST:
                System.out.println(ui.showList(tasks));
                break;
            case MARK:
                try {
                    Task task = tasks.markDone(this.index - 1);
                    System.out.println(ui.showDone(tasks, task.toString()));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case UNMARK:
                try {
                    Task task = tasks.markUndone(this.index - 1);
                    System.out.println(ui.showUndone(tasks, task.toString()));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DELETE:
                try {
                    Task task = tasks.deleteTask(this.index - 1);
                    System.out.println(ui.showDelete(tasks, task.toString()));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case TODO:
                Task todo = new Todo(this.description);
                tasks.addTask(todo);
                System.out.println(ui.showAdd(tasks, todo.toString()));
                break;
            case DEADLINE:
                Task deadlineTask = new Deadline(description, startTime);
                tasks.addTask(deadlineTask);
                System.out.println(ui.showAdd(tasks, deadlineTask.toString()));
                break;
            case EVENT:
                Task eventTask = new Event(description, startTime, endTime);
                tasks.addTask(eventTask);
                System.out.println(ui.showAdd(tasks, eventTask.toString()));
                break;
            case HELP:
                System.out.println(ui.showHelp());
                break;
            case DUE:
                System.out.println(ui.showDue(tasks, endTime));
                break;
            case FIND:
                System.out.println(ui.showFind(tasks, description));
                break;
            default:
                System.out.println(ui.showInvalid());
                break;
        }
    }
}

