package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * unmark command
 * -> unmarks the certain task
 */
public class UnmarkCommand extends Commands{

    protected int posNo;

    /**
     * @param posNo -> position number of the selected task
     */
    public UnmarkCommand(int posNo){
        this.posNo = posNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            tasks.taskslist.get(posNo).notMark();
            ui.printMsg("Ok, I've marked this task as not done yet:" + tasks.taskslist.get(posNo).toString());
            ui.showLine();
        } catch (IndexOutOfBoundsException e) {
            Ui.printMsg("The number is greater than the number of tasks in the list :(");
        }  
    }
}
