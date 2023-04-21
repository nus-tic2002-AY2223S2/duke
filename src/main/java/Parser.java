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
                    throw new DukeException("☹ OOPS!!! You need to specify which task to mark as done.");
                } catch (NumberFormatException e) {
                    throw new DukeException("☹ OOPS!!! Index must be a integer number.");
                }
            case "unmark":
                try {
                    return new Command(Command.CommandType.UNMARK, Integer.parseInt(command[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You need to specify which task to mark as undone.");
                } catch (NumberFormatException e) {
                    throw new DukeException("☹ OOPS!!! Index must be a integer number.");
                }
            case "delete":
                try {
                    return new Command(Command.CommandType.DELETE, Integer.parseInt(command[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You need to specify which task to delete.");
                } catch (NumberFormatException e) {
                    throw new DukeException("☹ OOPS!!! Index must be a integer number.");
                }
            case "todo":
                try {
                    return new Command(Command.CommandType.TODO, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            case "deadline":
                try {
                    String deadlineDescription = command[1].split(" /by ", 2)[0];
                    String deadlineTime = command[1].split(" /by ", 2)[1];
                    return new Command(Command.CommandType.DEADLINE, deadlineDescription, deadlineTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description and time of a deadline cannot be empty.");
                }
            case "event":
                try {
                    String eventDescription = command[1].split(" /from ", 2)[0];
                    String eventStartTime = command[1].split(" /from ", 2)[1].split(" /to ", 2)[0];
                    String eventEndTime = command[1].split(" /from ", 2)[1].split(" /to ", 2)[1];
                    return new Command(Command.CommandType.EVENT, eventDescription, eventStartTime, eventEndTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description, start time and end time of an event cannot be empty.");
                }
            case "find":
                try {
                    return new Command(Command.CommandType.FIND, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You need to specify which task to find.");
                }
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}