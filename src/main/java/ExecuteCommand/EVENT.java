package ExecuteCommand;

import Exception.DukeException;
import Task.Event;
import Storage.*;
import Parser.*;

public class EVENT extends Command{
    private final String inputCommand;
    public static final Storage storage = Storage.getStorage();

    public EVENT(String inputCommand) {
        this.inputCommand = inputCommand;
    }
    public void execute() throws DukeException {
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        String beforeFrom,afterFrom;
        beforeFrom = Parser.restCommand(inputCommand).split("/from",2)[0];
        afterFrom = Parser.restCommand(inputCommand).split("/from",2)[1];
        afterFrom = afterFrom.replace("/to","to:");
        Event event;
        if(Parser.restCommand(inputCommand).length()==2){
            event = new Event(beforeFrom,afterFrom);
        }else {
            event = new Event(beforeFrom,"");
        }
        storage.taskAdd(event);
    }
}
