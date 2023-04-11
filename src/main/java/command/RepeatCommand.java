package command;
import storage.Storage;
import task.*;
import ui.Ui;

/**
 * Update Command
 * -> To manage repeated task
 */
public class RepeatCommand extends Commands{
    protected int posNo;

    public RepeatCommand(int posNo){
        this.posNo = posNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.taskslist.get(posNo);
        Ui.printMsg("Task to repeat is " + task.toString());
        tasks.taskslist.add(task);
    }
}
