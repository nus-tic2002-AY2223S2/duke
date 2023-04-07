package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Deadline Command
 * -> takes in the deadline input, adds data into the list and returns message
 *
 */
public class DeadlineCommand extends Commands{
    protected String by;
    protected String description;
    /**
     * @param description -> deadline's description
     * @param by -> deadline's due date
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.addedTaskMsg();
        tasks.addDeadline(description, by);
        Ui.printMsg(tasks.taskslist.get(tasks.taskslist.size()-1).toString());
        Ui.printMsg("Number of tasks in the list = " + tasks.taskslist.size());
    }

}
