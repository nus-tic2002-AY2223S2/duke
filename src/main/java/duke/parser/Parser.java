/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.parser;

import duke.DukeException;
import duke.command.Command;

import java.io.File;
import java.util.ArrayList;

public class Parser {

    /**
     *  Attribute
     */

    /**
     *  Constructor
     */
    public Parser() {

    }

    /**
     *  This method will make sense of the command given and return a command object
     */
    public static Command parse(String fullCommand) throws DukeException {

        if(fullCommand.equalsIgnoreCase("bye") || fullCommand.equalsIgnoreCase("list")) {
            //creating Command object
            Command c = new Command(fullCommand);
            return c;
        }
        else {
            try {
                String [] seperatedInput = identifyFunctionsValidateInput(fullCommand);
                //creating Command object
                Command c = new Command(seperatedInput);
                return c;
            } catch (DukeException de) {
                throw new DukeException(de.getMessage());
            }
        }
    }


    /**
     *  Helper method
     *  isNumber() method will check input if it is a Number
     *  Returns TRUE if it is a number; FALSE if it is a String
     *  Logic:      Integer.parseInt will throw NumberFormatException if it is unable to convert input to Integer
     *              Once NumberFormatException is caught, return FALSE as it is not a Number
     *  Reference:  https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
     */
    public static boolean isNumber(String strToCheck)
    {
        try {
            Integer.parseInt(strToCheck);
            return true;
        }
        catch(NumberFormatException exception)
        {
            return false;
        }
    }

    /**
     *  Helper method
     *  identifyFunctionsValidateInput() method will perform 2 actions:
     *  1) return Functions and the parameters
     *  functions: "duke.Deadline" "duke.Event" "To-Do" "Mark" "Unmark"
     *  Outcome: separatedInput[0] = Functions
     *           separatedInput[1] = Parameters
     *
     *  2) validates the input to catch all kinds of error such as
     *  - Empty Parameters
     *  - Invalid Functions
     *  Deadline functions:
     *  - Missing /by parameters
     *  - Missing deadline
     *  Events functions:
     *  - Missing /from & /to parameters
     *  - Missing duration
     *  Mark / Unmark / Delete functions:
     *  - Missing value for mark & unmark
     *  - Too much values for mark & unmark
     */
    public static String[] identifyFunctionsValidateInput(String stringInput) throws DukeException
    {
        String[] separatedInput = stringInput.split(" ", 2);
        if(separatedInput[0].equalsIgnoreCase("Deadline"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0] + " cannot be empty.");
            }
            else if (!separatedInput[1].contains("/by"))
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0] + " is missing a \"/by\" for deadline");
            }
            else if(separatedInput[1].split("/by").length < 2)
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0] + " is missing a deadline");
            }
            else
            {
                return separatedInput;
            }
        }
        else if(separatedInput[0].equalsIgnoreCase("Event"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0] + " cannot be empty.");
            }
            else if (!separatedInput[1].contains("/from"))
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0] + " is missing a \"/from\" for Event");
            }
            else if (!separatedInput[1].contains("/to"))
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0] + " is missing a \"/to\" for Event");
            }
            else
            {
                //separate "/from" & "/to" to check for variable before returning separatedInput
                return separatedInput;
            }
        }
        else if(separatedInput[0].equalsIgnoreCase("Todo"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0] + " cannot be empty.");
            }
            else
            {
                return separatedInput;
            }
        }
        else if (separatedInput[0].equalsIgnoreCase("Mark") || separatedInput[0].equalsIgnoreCase("Unmark") || separatedInput[0].equalsIgnoreCase("Delete"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The value after " + separatedInput[0] + " cannot be empty.");
            }
            else if (!isNumber(separatedInput[1]))
            {
                throw new DukeException("☹ OOPS!!! The value after " + separatedInput[0] + " must be a number.");
            }
            else if (Integer.parseInt(separatedInput[1]) <= 0)
            {
                throw new DukeException("☹ OOPS!!! The value after " + separatedInput[0] + " cannot be smaller than 0.");
            }
            else
            {
                return separatedInput;
            }
        }
        else
        {
            //any other starting words besides the functions list, throws error
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
