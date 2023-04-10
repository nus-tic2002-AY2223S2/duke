package duke.Parser;

import duke.DukeException;

import java.util.ArrayList;


public class Parser {
    /**
     * This method validated the input given by the user
     * only the specified commands are allowed, exceptions will be thrown if anything word is parsed
     * @param input takes in the string input that was given by user
     * @param list takes in the ArrayList for checking the size of the list to valid indices
     * @return a boolean on whether the input is valid
     * @throws DukeException is displayed according to the type of error input
     */
    public static Boolean validateQuestion(String input, ArrayList list) throws DukeException {

        String[] splitted = input.split(" ", 2);
        if (!splitted[0].equalsIgnoreCase("bye") &&
                !splitted[0].equalsIgnoreCase("todo") &&
                !splitted[0].equalsIgnoreCase("deadline") &&
                !splitted[0].equalsIgnoreCase("event") &&
                !splitted[0].equalsIgnoreCase("list") &&
                !splitted[0].equalsIgnoreCase("mark") &&
                !splitted[0].equalsIgnoreCase("unmark") &&
                !splitted[0].equalsIgnoreCase("delete") &&
                !splitted[0].equalsIgnoreCase("priority") &&
                !splitted[0].equalsIgnoreCase("find")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (splitted.length < 2 && !splitted[0].equalsIgnoreCase("list")) {
            throw new DukeException("☹ OOPS!!! The description of a " + splitted[0] + " cannot be empty.");
        }
        if (splitted[0].equalsIgnoreCase("mark") || splitted[0].equalsIgnoreCase("unmark")) {
            if ( Integer.parseInt(splitted[1]) > list.size() || Integer.parseInt(splitted[1]) == 0) {
                throw new DukeException("☹ OOPS!!! Index is not within the size of items in the list");
            }
        }
        return true;
    }
}
