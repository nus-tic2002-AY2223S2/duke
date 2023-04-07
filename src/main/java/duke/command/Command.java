/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;

public class Command {

    /**
     *  Attribute
     */
    private boolean byeAttribute;
    private String[] seperatedInput;
    private String commandName;

    /**
     *  Constructor w 2 input
     */
    public Command(String[] seperatedInput) {
        this.seperatedInput = seperatedInput;
        this.commandName = seperatedInput[0];
    }

    /**
     *  Constructor w 1 input (Bye, List etc.)
     */
    public Command(String input) {
        this.commandName = input;
        byeAttribute = checkIsBye(input);
    }

    /**
     *  This method checks if input is bye and returns TRUE if its bye; FALSE if it's not bye
     */
    public boolean checkIsBye(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *  This method performs all the key functions of the Duke programme
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        if (!commandName.equalsIgnoreCase("bye")) {
            if (commandName.equalsIgnoreCase("list")) {
                task.printTaskList();
            }
            else if (commandName.equalsIgnoreCase("mark")) {
                /**
                 * retrieve the index to be marked &
                 * set Status of Task to Done
                 */
                int indexInList = Integer.parseInt(seperatedInput[1]) - 1 ;
                task.markTask(indexInList);
            }
            else if (commandName.equalsIgnoreCase("unmark")) {
                /**
                 * retrieve the index to be unmarked &
                 * set Status of Task to Undone
                 */
                int indexInList = Integer.parseInt(seperatedInput[1]) - 1 ;
                task.unmarkTask(indexInList);
            }
            else if (commandName.equalsIgnoreCase("delete")) {

                /**
                 * retrieve the index to be deleted &
                 * remove the Task from the list
                 */
                int indexInList = Integer.parseInt(seperatedInput[1]) - 1 ;
                task.deleteTask(indexInList);
                System.out.println("Now you have " + task.getSizeOfList() + " task(s) in the list");
            }
            else if (commandName.equalsIgnoreCase("deadline")) {
                /**
                 * Using indexOf method to extract description & dateline
                 * nextSeparated array will store value in such index
                 * nextSeparated[0] = description;
                 * nextSeparated[1] = deadline;
                 */
                String [] nextSeparated = seperatedInput[1].split("/by");

                Deadline newDeadline = new Deadline(nextSeparated[0].trim(), nextSeparated[1].trim());
                task.addNewTask(newDeadline);
                System.out.println("Now you have " + task.getSizeOfList() + " task(s) in the list");
            }
            else if(commandName.equalsIgnoreCase("event")) {
                /**
                 * Using indexOf method to extract description & start/end timing
                 * nextSeparated array will store value in such index
                 * @param nextSeparated[0] = description;
                 * @param nextSeparated[1] = start/end timing;
                 */
                String [] nextSeparated = seperatedInput[1].split("/from");
                String [] separatedTiming = nextSeparated[1].split("/to");

                Event newEvent = new Event(nextSeparated[0].trim(), separatedTiming[0].trim(), separatedTiming[1].trim());
                task.addNewTask(newEvent);
                System.out.println("Now you have " + task.getSizeOfList() + " task(s) in the list");
            }
            else if(commandName.equalsIgnoreCase("todo")) {

                Todo newTodo = new Todo(seperatedInput[1]);
                task.addNewTask(newTodo);
                System.out.println("Now you have " + task.getSizeOfList() + " task(s) in the list");
            }
            else if(commandName.equalsIgnoreCase("find"))
            {
                task.findItemInList(seperatedInput[1].trim());
            }

            /**
             *  save task list to txt
             */
            storage.saveFile(task);
        }
        else {
            byeAttribute = true;
            ui.showByeMeg();
        }
    }

    /**
     *  This method returns the current status of byeAttribute
     *  returns TRUE if it is bye
     *  returns FALSE if it is not bye
     */
    public boolean isItBye() {
        return byeAttribute;
    }

}
