package ExecuteCommand;

import Storage.*;
import Exception.DukeException;
import Parser.*;

public class DELETE extends Command{
    private final String inputCommand;
    public static final Storage storage = Storage.getStorage();

    public DELETE(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    public void execute() throws DukeException{
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! Deleted number can not be empty.");
        }
        int deleteNum = Integer.parseInt(Parser.restCommand(inputCommand)) - 1;
        if(deleteNum >= storage.tasksList.size()){
            throw new DukeException("☹ OOPS!!! Please key in correct number!");
        }
        storage.deleteTask(deleteNum);
    }
}
