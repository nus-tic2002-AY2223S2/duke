package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * To exit the program
 *
 */
public class ByeCommand extends Commands {
    protected static boolean isExit = false;
    public static boolean exit() {
        return isExit;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg("GoodBye! Have a great day :)");
        ui.showLine();
        isExit = true;
    }

    public static boolean getExitStatus() {
        return isExit;
    }

}
