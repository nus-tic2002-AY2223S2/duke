package duke.util;

import duke.command.Command;
import duke.exceptions.DukeException;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for parsing the user input and creating the appropriate Command object to be executed by Duke.
 * The parse method takes in a String of user input and uses a switch statement to determine the type of Command to create based on the command keyword.
 * If the input String is not recognized as a valid command, a DukeException is thrown with an error message.
 */
public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] command = fullCommand.split(" ", 2);
        switch (command[0]) {
            case "help":
                return new Command(Command.CommandType.HELP);
            case "bye":
                return new Command(Command.CommandType.BYE);
            case "list":
                return new Command(Command.CommandType.LIST);
            case "mark":
                try {
                    return new Command(Command.CommandType.MARK, Integer.parseInt(command[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! You need to specify which task to mark as done.");
                } catch (NumberFormatException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! Index must be a integer number.");
                }
            case "unmark":
                try {
                    return new Command(Command.CommandType.UNMARK, Integer.parseInt(command[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! You need to specify which task to mark as undone.");
                } catch (NumberFormatException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! Index must be a integer number.");
                }
            case "delete":
                try {
                    return new Command(Command.CommandType.DELETE, Integer.parseInt(command[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! You need to specify which task to delete.");
                } catch (NumberFormatException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! Index must be a integer number.");
                }
            case "todo":
                try {
                    return new Command(Command.CommandType.TODO, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! The description of a todo cannot be empty.");
                }
            case "deadline":
                try {
                    String deadlineDescription = command[1].split(" /by ", 2)[0];
                    String deadlineTime = command[1].split(" /by ", 2)[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    return new Command(Command.CommandType.DEADLINE, deadlineDescription, LocalDateTime.parse(deadlineTime, formatter));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! The description and time of a deadline cannot be empty.");
                } catch (DateTimeException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! The time of a deadline must be in the format yyyy-mm-dd hh:mm.");
                }
            case "event":
                try {
                    String eventDescription = command[1].split(" /from ", 2)[0];
                    String eventStartTime = command[1].split(" /from ", 2)[1].split(" /to ", 2)[0];
                    String eventEndTime = command[1].split(" /from ", 2)[1].split(" /to ", 2)[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    return new Command(Command.CommandType.EVENT, eventDescription, LocalDateTime.parse(eventStartTime, formatter), LocalDateTime.parse(eventEndTime, formatter));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! The description, start time and end time of an event cannot be empty.");
                } catch (DateTimeException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! The time of a event must be in the format yyyy-mm-dd hh:mm.");
                }
            case "find":
                try {
                    return new Command(Command.CommandType.FIND, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! You need to specify the keyword.");
                }
            case "due":
                try {
                    String endTime = command[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    return new Command(Command.CommandType.DUE, LocalDateTime.parse(endTime, formatter));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! You need to specify the date.");
                } catch (DateTimeException e) {
                    throw new DukeException("\t Ծ‸Ծ OOPS!!! The time must be in the format yyyy-mm-dd hh:mm.");
                }
            default:
                throw new DukeException("\t Ծ‸Ծ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n\t You can entre keyword 'help' for more details!･◡･");
        }
    }
}