package command;

import exception.DukeException;
import storage.Storage;
import ui.Ui;
import task.Task;
import tasklist.TaskList;

public class DeleteCommand extends Command {
    private int index;

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
