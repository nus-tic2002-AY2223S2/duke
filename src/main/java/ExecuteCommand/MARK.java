package ExecuteCommand;

import Exception.DukeException;
import Storage.*;
import Parser.*;

public class MARK extends Command{
    private final String inputCommand;

    public static final Storage storage = Storage.getStorage();

    public MARK(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * Mark task and save
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException  {
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! Mark number can not be empty.");
        }
        int markNum = Integer.parseInt(Parser.restCommand(inputCommand)) - 1;
        if(markNum >= storage.tasksList.size()){
            throw new DukeException("☹ OOPS!!! Please key in correct number!");
        }
        storage.mark(markNum);
    }
}
