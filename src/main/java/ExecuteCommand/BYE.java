package ExecuteCommand;

import Exception.DukeException;
import UI.*;
public class BYE extends Command{
    @Override
    public void execute() throws DukeException {
        Ui.Echo("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
