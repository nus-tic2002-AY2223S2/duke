package command;

import storage.Storage;
import ui.Ui;
import tasklist.TaskList;
import exception.DukeException;
public abstract class Command {
    /**
     * Executes the command with the given TaskList, Ui, and Storage.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui responsible for user interaction.
     * @param storage The Storage responsible for saving and loading tasks.
     * @throws DukeException If an error occurs during the command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
