/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.command;

import duke.Task;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class Command {

    /**
     *  Attribute
     */
    private static boolean byeAttribute;
    private static String[] seperatedInput;
    private static String commandName;

    /**
     *  Constructor w 2 input
     */
    public Command(String[] seperatedInput) {
        this.seperatedInput = seperatedInput;
        commandName = seperatedInput[0];
    }

    /**
     *  Constructor w 1 input (Bye, List etc.)
     */
    public Command(String input) {
        this.commandName = commandName;
        byeAttribute = checkIsBye(commandName);
    }

    public static boolean checkIsBye(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void execute(TaskList task, Ui ui, Storage storage) {

    }

    public static boolean isItBye() {
        return byeAttribute;
    }

}
