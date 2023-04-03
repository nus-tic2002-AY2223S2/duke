package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Commands{

    protected int posNo;

    public UnmarkCommand(int posNo){
        this.posNo = posNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.taskslist.get(posNo).notMark();
        ui.printMsg("Ok, I've marked this task as not done yet:" + tasks.taskslist.get(posNo).toString());
    }
}
