package ExecuteCommand;

import Exception.DukeException;
import Storage.*;
import Parser.*;

public class UNMARK extends Command{
    private final String inputCommand;
    public static final Storage storage = Storage.getStorage();

    public UNMARK(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * unmark task number and save
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! Unmark number can not be empty.");
        }
        int markNum = Integer.parseInt(Parser.restCommand(inputCommand)) - 1;
        if(markNum >= storage.tasksList.size()){
            throw new DukeException("☹ OOPS!!! Please key in correct number!");
        }
        storage.unmark(markNum);
    }
}
