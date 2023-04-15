package parser;

import command.*;
import command.UpdateCommand;
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
        String[] givenCommand = input.split(" ", 2);
        String firstCommand = givenCommand[0];

        try {
            switch (firstCommand) {
                case "help":
                    Ui.help();
                    Ui.showLine();
                    return new Commands();
                case "list":
                    return new listArray();
                case "bye":
                    return new ByeCommand();
                case "mark":
                    int markNum = Integer.parseInt(givenCommand[1].trim()) - 1;
                    return new MarkCommand(markNum);
                case "unmark":
                    int num = Integer.parseInt(givenCommand[1].trim()) - 1;
                    return new UnmarkCommand(num);
                case "deadline":
                    String[] givenBy = givenCommand[1].split("/by"); // split the command at /by
                    String lastDay = LocalDateTime.formatDate(givenBy[1].trim()); // get the due date/time
                    return new DeadlineCommand(givenBy[0].trim(), lastDay);
                case "todo":
                    return new TodoCommand(givenCommand[1].trim()); //get description
                case "event":
                    String[] givenFrom = givenCommand[1].split("/from"); // split the command at /from
                    String[] fromTo = givenFrom[1].split("/to"); //split at /to to get from and to
                    String fromEvent = LocalDateTime.formatDate(fromTo[0].trim()); //get the from in a formatdatetime
                    String toEvent = LocalDateTime.formatDate(fromTo[1].trim()); // get the to in a formatdatetime
                    return new EventCommand(givenFrom[0].trim(), fromEvent, toEvent);
                case "delete":
                    int selectedNum = Integer.parseInt(givenCommand[1].trim()) - 1; //get the position
                    return new DeleteCommand(selectedNum);
                case "update":
                    String[] updateChosenTask = givenCommand[1].split("-"); //split accordingly
                    int updateNum = Integer.parseInt(updateChosenTask[0].trim()) - 1; //get the task number
                    return new UpdateCommand(updateNum, updateChosenTask[1].trim(), updateChosenTask[2].trim());
                case "find":
                    return new FindCommand(givenCommand[1].trim()); //gets the keyword
                case "repeat":
                    int selectedRepeat = Integer.parseInt(givenCommand[1].trim()) - 1; //get selected task
                    return new RepeatCommand(selectedRepeat);
                default:
                    throw new DukeException();
            }
        } catch (DukeException e) {
            Ui.showLoadingError();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMsg("No description given :(");
        } catch (IndexOutOfBoundsException e) {
            Ui.printMsg("The number is greater than the number of tasks in the list :(");
        }
        return new ErrorCommand(input);
    }
}
