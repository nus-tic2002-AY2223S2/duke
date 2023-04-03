package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Commands {
    private static boolean exit = false;
    public static boolean exit() {
        return exit;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg("GoodBye! Have a great day :)");
        exit = true;
    }

}
