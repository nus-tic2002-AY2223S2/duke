package Duke.command;

import Duke.tasklist.Tasklist;
import Duke.ui.UI;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    @Override
    public void execute(Tasklist tasks, UI ui) {
        System.out.println("Bye. Hope to see you again soon!");
        this.isExit = true;
    }

    @Override
    public boolean isExit() {
        return isExit; // if Exit, return true
    }
}