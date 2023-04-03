package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

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