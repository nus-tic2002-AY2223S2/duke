package command;

import exception.DukeException;
import storage.Storage;
import ui.Ui;
import task.Task;
import tasklist.TaskList;
/**
 * Represents a mark command that marks a task in the task list as done.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String index) {
        this.index = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.markAsDone(index);
            ui.showTaskMarked(task);
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Error: Invalid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
