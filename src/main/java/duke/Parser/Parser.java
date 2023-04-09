package duke.Parser;

import duke.DukeException;

import java.util.ArrayList;

public class Parser {
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
            if ( Integer.parseInt(splitted[1]) > list.size() ) {
                throw new DukeException("☹ OOPS!!! Index is over the size of items in the list");
            }
        }
        return true;
    }
}
