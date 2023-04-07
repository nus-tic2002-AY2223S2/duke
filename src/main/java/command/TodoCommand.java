package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * To Do Command
 * -> adds the task into the list and returns message
 */
public class TodoCommand extends Commands{
    protected String description;

    /**
     * @param description -> to do's description
     */
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
