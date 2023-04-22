package executecommand;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ScheduleCommand extends Command{

    public ScheduleCommand(String inputCommand) {
        super(inputCommand);
    }

    public void execute()throws DukeException {
        inputCommandStorage(inputCommand, storage);
    }

    static void inputCommandStorage(String inputCommand, Storage storage) throws DateTimeParseException {
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! The date and time of schedule cannot be empty.");
        }
        String restCommand;
        restCommand = Parser.restCommand(inputCommand);
        String[] scheduleDetails = Parser.getSchedule(restCommand);
        try{
            LocalDateTime scheduleDate = LocalDateTime.parse(scheduleDetails[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            storage.scheduleView(scheduleDate);
        }catch (DateTimeParseException  e){
            throw new DukeException("☹ OOPS!!! Please key in the correct pattern!(schedule \"yyyy-MM-dd HH:mm\")");
        }
    }
}
