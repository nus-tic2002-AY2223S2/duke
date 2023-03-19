package ExecuteCommand;

import Task.Deadline;
import Exception.DukeException;
import Storage.*;
import Parser.*;

public class DEADLINE extends Command{
    private final String inputCommand;
    public static final Storage storage = Storage.getStorage();

    public DEADLINE(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    public void execute()throws DukeException {
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String beforeBy,afterBy;
        beforeBy = Parser.restCommand(inputCommand).split("/by")[0];
        afterBy = Parser.restCommand(inputCommand).split("/by")[1];
        Deadline deadline;
        if(Parser.restCommand(inputCommand).length()==2){
            deadline = new Deadline(beforeBy,afterBy);
        }else {
            deadline = new Deadline(beforeBy,"");
        }
        storage.taskAdd(deadline);
    }
}
