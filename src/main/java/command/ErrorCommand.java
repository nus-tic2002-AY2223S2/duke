package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Error Command
 * -> returns an error message when the code hits an error
 */
public class ErrorCommand extends Commands{
    protected String input;

    public ErrorCommand(String input){
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg("Invalid command :(");
    }

}