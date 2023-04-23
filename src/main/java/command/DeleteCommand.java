package command;

import exception.DukeException;
import storage.Storage;
import ui.Ui;
import task.Task;
import tasklist.TaskList;
/**
 * Represents a delete command that removes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Deletes a task from the task list and updates the storage.
     *
     * @param tasks   The task list where the command will operate on.
     * @param ui      The user interface for user interaction.
     * @param storage The storage for reading and writing tasks.
     * @throws DukeException If there is an error executing the command.
     */

    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task removedTask = tasks.delete(index);
        ui.showTaskDeleted(removedTask, tasks.size());
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
