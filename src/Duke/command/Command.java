package Duke.command;

import Duke.tasklist.Tasklist;
import Duke.storage.Storage;
import Duke.ui.UI;

public abstract class Command {

    protected boolean isExit = false;
    protected Tasklist tasks;
    protected Storage storage;
    protected UI ui;

    public abstract void execute(Tasklist tasks, UI ui);

    public boolean isExit() {
        return this.isExit;
    }
}