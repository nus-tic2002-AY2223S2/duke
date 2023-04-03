package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Commands{
    protected int posNo;

    public MarkCommand(int posNo){
        this.posNo = posNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.taskslist.get(posNo).mark();
        ui.printMsg("Ok, I've marked this task as not done yet:" + tasks.taskslist.get(posNo).toString());
    }
}
