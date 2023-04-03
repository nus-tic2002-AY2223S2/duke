package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class TodoCommand extends Commands{
    protected String description;

    public TodoCommand(String description){
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.addedTaskMsg();
        tasks.addTodo(description);
        Ui.printMsg(tasks.taskslist.get(tasks.taskslist.size()-1).toString());
        Ui.printMsg("Number of tasks in the list = " + tasks.taskslist.size());
    }

}
