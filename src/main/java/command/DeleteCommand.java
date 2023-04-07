package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Delete Command
 * -> removes selected task from the list and returns message
 */
public class DeleteCommand extends Commands{
    protected int posNo;
/**
 * @param posNo -> the task's position number in the list
 */
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