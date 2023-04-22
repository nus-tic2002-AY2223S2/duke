package Duke.parser;

import Duke.command.*;
import Duke.dukeexception.DukeException;

public class Parser {

    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String DELETE = "delete";
    private static final String SAVE = "save";

    public Parser() {

    }

    /**
     * Read user's input comand and call the respective function constructor
     * @param instruction of the user command
     * @return the command object
     */
    public static Command parse(String instruction) throws DukeException {
        String[] arr = instruction.split(" ", 2);
        String command = arr[0];
        try {
            switch (command) {
                case LIST:
                    return new ListCommand();
                case BYE:
                    return new ExitCommand();
                case SAVE:
                    return new SaveCommand();
                case DELETE:
                    return new DeleteCommand(Integer.parseInt(arr[1]) - 1);
                default:
                    if (arr[1].isEmpty()){
                        throw new DukeException();
                    }
                    return new AddCommand(command, arr[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide commands in the format: <command type> <task details>");
        }
    }
}