package parser;

import command.*;


/**
 * Represents a parser that processes and interprets user inputs into commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input as a string.
     * @return The corresponding Command object based on the user input.
     */
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input.substring(5));
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input.substring(7));
        } else if (input.startsWith("todo ")) {
            return new AddCommand(input);
        } else if (input.startsWith("deadline ")) {
            return new AddCommand(input);
        } else if (input.startsWith("event ")) {
            return new AddCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input.substring(7));
        } else if (input.startsWith("priority ")) {
            return new UpdatePriorityCommand(input);
        } else if (input.startsWith("find ")) {
            return new FindCommand(input);
        } else {
            return new InvalidCommand();
        }
    }
}