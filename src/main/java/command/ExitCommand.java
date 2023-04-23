package command;

import storage.Storage;
import ui.Ui;
import tasklist.TaskList;
/**
 * Represents an exit command that terminates the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
