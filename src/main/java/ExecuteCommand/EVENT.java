package ExecuteCommand;

import Exception.DukeException;
import Parser.Parser;
import Storage.Storage;
import Task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EVENT extends Command {
    public static final Storage storage = Storage.getStorage();
    private final String inputCommand;

    public EVENT(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * generate event task and add in tasks list
     * @throws Duke Exception
     */
    public void execute() throws DukeException {
        if (Parser.commandLength(inputCommand) < 2) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        String restCommand;
        restCommand = Parser.restCommand(inputCommand);
        String[] eventDetails = Parser.getEvent(restCommand);
        Event event;
        LocalDateTime d1 = LocalDateTime.parse(eventDetails[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime d2 = LocalDateTime.parse(eventDetails[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if(d1.isAfter(d2)){
            throw new DukeException("Please check the date and time again!");
        }
        try {
            event = new Event(eventDetails[0],
                    LocalDateTime.parse(eventDetails[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    LocalDateTime.parse(eventDetails[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Please key in the correct pattern! (event XXX /from \"yyyy-MM-dd HH:mm\" /to \"yyyy-MM-dd HH:mm\")");
        }
        storage.taskAdd(event);
    }
}
