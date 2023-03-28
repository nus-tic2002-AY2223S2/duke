package ExecuteCommand;

import Task.Deadline;
import Exception.DukeException;
import Storage.*;
import Parser.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DEADLINE extends Command{
    private final String inputCommand;
    public static final Storage storage = Storage.getStorage();

    public DEADLINE(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * add deadline task to tasklist and save
     * @throws DukeException
     */
    public void execute()throws DukeException {
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String restCommand;
        restCommand = Parser.restCommand(inputCommand);
        String[] deadlineDetails = Parser.getDeadline(restCommand);
        Deadline deadline;
        try{
            deadline = new Deadline(deadlineDetails[0],
                    LocalDateTime.parse(deadlineDetails[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }catch (Exception e){
            throw new DukeException("☹ OOPS!!! Please key in the correct pattern!(deadline XXX /by \"yyyy-MM-dd HH:mm\")");
        }

        storage.taskAdd(deadline);
    }
}
