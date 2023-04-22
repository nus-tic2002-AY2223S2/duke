package command;

import storage.Storage;
import ui.Ui;
import tasklist.TaskList;
import exception.DukeException;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
