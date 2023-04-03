package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Commands{
    protected int posNo;

    public DeleteCommand(int posNo){
        this.posNo = posNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg("Noted. I've removed this task:");
        tasks.delete(posNo);
        Ui.printMsg("Number of tasks in the list = " + tasks.taskslist.size());
    }

}