public class Parser {
    public static Command parse(String fullCommand) {
        String[] command = fullCommand.split(" ", 2);
        switch (command[0]) {
            case "help":
                return new Command(Command.CommandType.HELP);
            case "bye":
                return new Command(Command.CommandType.BYE);
            case "list":
                return new Command(Command.CommandType.LIST);
            case "done":

                return new Command(Command.CommandType.DONE, Integer.parseInt(command[1]));

            case "undone":

                    return new Command(Command.CommandType.UNDONE, Integer.parseInt(command[1]));

            case "delete":

                    return new Command(Command.CommandType.DELETE, Integer.parseInt(command[1]));

            case "todo":

                    return new Command(Command.CommandType.TODO, command[1]);

            case "deadline":

                    String deadlineDescription = command[1].split(" /by ", 2)[0];
                    String deadlineTime = command[1].split(" /by ", 2)[1];
                    return new Command(Command.CommandType.DEADLINE, deadlineDescription, deadlineTime);

            case "event":

                    String eventDescription = command[1].split(" /from ", 2)[0];
                    String eventStartTime = command[1].split(" /from ", 2)[1].split(" /to ", 2)[0];
                    String eventEndTime = command[1].split(" /from ", 2)[1].split(" /to ", 2)[1];
                    return new Command(Command.CommandType.EVENT, eventDescription, eventStartTime, eventEndTime);

            default:
                return new Command(Command.CommandType.INVALID);
        }
    }
}
