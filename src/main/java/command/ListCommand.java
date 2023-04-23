package command;

import storage.Storage;
import ui.Ui;
import tasklist.TaskList;
/**
 * Represents a list command that displays the current tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
