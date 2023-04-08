package parser;

import command.*;
import exception.DukeException;
import ui.Ui;
import storage.Storage;
import formatDateTime.LocalDateTime;

//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import  java.time.format.FormatStyle;
import  java.util.Locale;

/**
 * parser class
 * -> return the respective information of each commands
 */

public class Parser {
    /**
     *
     * @param input -> commands given
     * @return
     */
    public static Commands parse(String input) {
        String[] givenCommand = input.split(" ");
        String firstCommand = givenCommand[0];

        try {
            switch (firstCommand) {
                /*case "help":
                    return new Ui.help();*/
                case "list":
                    return new listArray();
                case "bye":
                    return new ByeCommand();
                case "mark":
                    String markPosNo = givenCommand[1].trim();
                    int markNum = Integer.parseInt(markPosNo) - 1;
                    return new MarkCommand(markNum);
                case "unmark":
                    String posNo = givenCommand[1].trim();
                    int num = Integer.parseInt(posNo) - 1;
                    return new UnmarkCommand(num);
                case "deadline":
                    String[] givenBy = input.split("/by"); // split the command at /by
                    System.out.println("datetime: "+givenBy[1]);
                    String lastDay = LocalDateTime.formatDate(givenBy[1].trim()); // get the due date/time
                    String deadLineTask = givenBy[0].replace("deadline", "").trim(); //remove deadline to get the task
                    return new DeadlineCommand(deadLineTask, lastDay);
                case "todo":
                    String[] splitTask = input.split(" ", 2); //split by min 2 array
                    String todoTask = splitTask[1]; //get description
                    return new TodoCommand(todoTask);
                case "event":
                    String[] givenFrom = input.split("/from"); // split the command at /from
                    String eventTask = givenFrom[0].replace("event", "").trim();//remove event to get the task
                    String[] fromTo = givenFrom[1].split("/to"); //split at /to to get from and to
                    String fromEvent = LocalDateTime.formatDate(fromTo[0].trim()); //get the from
                    String toEvent = LocalDateTime.formatDate(fromTo[1].trim()); // get the to
                    return new EventCommand(eventTask, fromEvent, toEvent);
                case "delete":
                    String[] splitDelete = input.split(" ", 2); //split by min 2 array
                    String deleteTask = splitDelete[1].trim(); //get the position
                    int selectedNum = Integer.parseInt(deleteTask) - 1;
                    return new DeleteCommand(selectedNum);
                default:
                    throw new DukeException();
            }
        } catch (DukeException e) {
            Ui.showLoadingError();
        }
        return new ErrorCommand(input);
    }


}
