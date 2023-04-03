package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class EventCommand extends Commands{
    protected String description;
    protected String from;
    protected String to;

    public EventCommand(String description, String from, String to){
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.addedTaskMsg();
        tasks.addEvent(description, from, to);
        Ui.printMsg(tasks.taskslist.get(tasks.taskslist.size()-1).toString());
        Ui.printMsg("Number of tasks in the list = " + tasks.taskslist.size());
    }

}