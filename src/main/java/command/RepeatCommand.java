package command;
import storage.Storage;
import task.*;
import ui.Ui;

/**
 * Repeat Command
 * -> To manage repeated task
 */
public class RepeatCommand extends Commands{
    protected int posNo;

    public RepeatCommand(int posNo){
        this.posNo = posNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (posNo >=0 && posNo<tasks.taskslist.size()) {
            Task task = tasks.taskslist.get(posNo);
            Ui.printMsg("Selected task for recurring is " + task.toString());

            if (task.toString().contains("[T]")) { // to prevent repeated marking
                String repeatTask = task.getDescription();
                tasks.addTodo(repeatTask);
            } else if (task.toString().contains("[D]")) {
                Deadline d = (Deadline) task;
                String repeatTask = d.getDescription();
                String repeatDate = d.getBy();
                tasks.addDeadline(repeatTask, repeatDate);
            } else if (task.toString().contains("[E]")) {
                Event e = (Event) task;
                String repeatTask = e.getDescription();
                String repeatFrom = e.getFrom();
                String repeatTo = e.getTo();
                tasks.addEvent(repeatTask, repeatFrom,repeatTo);
            }
        } else {
            Ui.printMsg("Invalid task number " + posNo);
        }
        ui.showLine();
    }
}
