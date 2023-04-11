/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.parser;

import duke.DukeException;
import duke.command.Command;

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
     * This method will make sense of the command given and return a command object
     * @param fullCommand raw command input given by the user
     * @return a command object containing either the fullCommand or the seperated array of command & parameters
     * @throws DukeException to catch error from 'identifyFunctionsValidateInput' method
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
     * Helper method
     * isNumber() method will check input if it is a Number
     * Reference:  https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
     * @param strToCheck String input to check
     * @return TRUE if it is a number; FALSE if it is a String
     * @throws DukeException Integer.parseInt will throw NumberFormatException if it is unable to convert input to Integer once NumberFormatException is caught, return FALSE as it is not a Number
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
     * Helper method
     * identifyFunctionsValidateInput() method will perform 2 actions:
     * 1) return Functions and the parameters
     *  functions: "Deadline" "Event" "To-Do" "Mark" "Unmark" "Delete" "Find"
     *  Outcome: separatedInput[0] = Functions
     *           separatedInput[1] = Parameters
     *
     * 2) validates the input to catch all kinds of error such as
     *  - Empty Parameters
     *  - Invalid Functions
     * Deadline functions:
     *  - Missing /by parameters
     *  - Missing deadline
     * Events functions:
     *  - Missing /from & /to parameters
     *  - Missing duration
     * Mark / Unmark / Delete functions:
     *  - Missing value for mark & unmark
     *  - Each value (if more than 1, must be a number or more than 0)
     * @param stringInput input given by user
     * @return a sanitized string array for processing
     * @throws DukeException if any of the rule above is not met
     */
    public static String[] identifyFunctionsValidateInput(String stringInput) throws DukeException
    {
        String[] separatedInput = stringInput.split(" ", 2);
        if(separatedInput[0].trim().equalsIgnoreCase("Deadline"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0].trim() + " cannot be empty.");
            }
            else if (!separatedInput[1].contains("/by"))
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0].trim() + " is missing a \"/by\" for deadline");
            }
            else if(separatedInput[1].split("/by").length < 2)
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0].trim() + " is missing a deadline");
            }
            else
            {
                return separatedInput;
            }
        }
        else if(separatedInput[0].trim().equalsIgnoreCase("Event"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0].trim() + " cannot be empty.");
            }
            else if (!separatedInput[1].contains("/from"))
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0].trim() + " is missing a \"/from\" for Event");
            }
            else if (!separatedInput[1].contains("/to"))
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0].trim() + " is missing a \"/to\" for Event");
            }
            else
            {
                //separate "/from" & "/to" to check for variable before returning separatedInput
                return separatedInput;
            }
        }
        else if(separatedInput[0].trim().equalsIgnoreCase("Todo"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0].trim() + " cannot be empty.");
            }
            else
            {
                return separatedInput;
            }
        }
        else if (separatedInput[0].trim().equalsIgnoreCase("Mark") || separatedInput[0].trim().equalsIgnoreCase("Unmark") || separatedInput[0].trim().equalsIgnoreCase("Delete"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The value after " + separatedInput[0].trim() + " cannot be empty.");
            }
            else
            {
                String[] separateMultipleIndex = separatedInput[1].split(",");
                for(int i = 0; i < separateMultipleIndex.length; i++)
                {
                    if(!isNumber(separateMultipleIndex[i].trim()))
                    {
                        throw new DukeException("☹ OOPS!!! The value '" + separateMultipleIndex[i].trim() + "' must be a number.");
                    }
                    else if (Integer.parseInt(separateMultipleIndex[i].trim()) <= 0)
                    {
                        throw new DukeException("☹ OOPS!!! The value " + separateMultipleIndex[i].trim() + " cannot be smaller than 0.");
                    }
                }
                return separatedInput;
            }
        }
        else if(separatedInput[0].trim().equalsIgnoreCase("Find"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! Find command is unable to find an empty input.");
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
