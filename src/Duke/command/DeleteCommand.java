package Duke.command;

import Duke.task.Task;
import Duke.tasklist.Tasklist;
import Duke.ui.UI;

public class DeleteCommand extends Command {

    protected int indexToBeDeleted;

    public DeleteCommand(int indexToBeDeleted) {
        this.indexToBeDeleted = indexToBeDeleted;
    }

    @Override
    public void execute(Tasklist tasks, UI ui) {
        Task deletedTask = tasks.getTasks().get(indexToBeDeleted);

        tasks.getTasks().remove(this.indexToBeDeleted);

        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + tasks.getTasks().size() + " task in the list.");
    }
}