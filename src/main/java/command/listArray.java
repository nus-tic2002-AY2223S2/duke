package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
public class listArray extends Commands {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
         ui.lists(tasks.getTasks());
    }

}
