package Duke.command;

import Duke.tasklist.Tasklist;
import Duke.ui.UI;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(Tasklist tasks, UI ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++ ){
            System.out.println(i+1 + ". " + tasks.getTasks().get(i).toString());
        }
    }
}