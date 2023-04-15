package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * List Array Command
 * -> List all the tasks in the array
 */
public class listArray extends Commands {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
         if (tasks.taskslist.size() == 0) {
            ui.printMsg("There are no task in your list now. :( Please add some tasks");
        } else {
            ui.lists(tasks.getTasks());
        }
        Ui.showLine();
    }

}
