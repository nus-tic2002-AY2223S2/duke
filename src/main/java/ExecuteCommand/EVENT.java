package ExecuteCommand;

import Exception.DukeException;
import Task.Event;
import Storage.*;
import Parser.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String restCommand;
        restCommand = Parser.restCommand(inputCommand);
        String[] eventDetails = Parser.getEvent(restCommand);
        Event event;
        try{
            event = new Event(eventDetails[0],
                    LocalDateTime.parse(eventDetails[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }catch (Exception e){
            throw new DukeException("☹ OOPS!!! Please key in the correct pattern! (event XXX /from \"yyyy-MM-dd HH:mm\")");
        }
        storage.taskAdd(event);
    }
}
