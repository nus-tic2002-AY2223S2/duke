package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Command
 */
public class Commands {
    protected Commands command;
    public boolean exitTime=false;

    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    public boolean isExit() {
        return this.exitTime;
    }


}
