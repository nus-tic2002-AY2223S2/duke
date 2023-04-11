/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Command {

    /**
     *  Attribute
     */
    private boolean isByeAttribute;
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
        isByeAttribute = checkIsBye(input);
    }

    /**
     * This method checks if command line is 'bye'
     * @param input command parameter to check for 'bye' command
     * @return True if command is 'bye', False if command is not 'bye'
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
     * This helper method converts all values in the string to array
     * @param strArrayList arraylist of String elements
     * @return arraylist of Integer elements
     */
    public ArrayList<Integer> convertToIntArrayList(ArrayList<String> strArrayList) {
        ArrayList<Integer> intArrayList = new ArrayList<Integer>();
        for(String s : strArrayList) {
            intArrayList.add(Integer.valueOf(s));
        }
        return intArrayList;
    }

    /**
     * This helper method takes in a String array and splits them into integer Arraylist using a helper method 'convertToIntarrayList'
     * @param inputArray array of String elements
     * @return arraylist of Integer elements
     */
    public ArrayList<Integer> convertAndSortDescending(String[] inputArray) {
        ArrayList<String> separateMultipleIndex = new ArrayList(Arrays.asList(inputArray));
        separateMultipleIndex = (ArrayList<String>)separateMultipleIndex.stream().map(p -> p.trim()).collect(Collectors.toList()); //this method will use the inbuilt stream function in arrayList to remove all whitespaces in each element
        ArrayList<Integer> multipleIndexList = convertToIntArrayList(separateMultipleIndex);
        Collections.sort(multipleIndexList, Collections.reverseOrder());
        return multipleIndexList;
    }

    /**
     * This method performs all key functions of the Duke programe
     * @param task TaskList object for Task manipulation purposes
     * @param ui UI object for display purposes
     * @param storage Storage object for storing purposes
     * @return arraylist of Integer elements
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        if (!commandName.equalsIgnoreCase("bye")) {
            if (commandName.equalsIgnoreCase("list")) {
                task.printTaskList();
            }
            else if (commandName.equalsIgnoreCase("mark")) {
                /**
                 * split seperatedInput[1] by commas,
                 * sort by descending using Collections lib,
                 * iterate to retrieve the index to be marked &
                 * set Status of Task to Done
                 */
                ArrayList<Integer> multipleIndexList = convertAndSortDescending(seperatedInput[1].split(","));
                for(int i = 0; i < multipleIndexList.size(); i++) {
                    task.markTask(multipleIndexList.get(i)-1);
                }
            }
            else if (commandName.equalsIgnoreCase("unmark")) {
                /**
                 * split seperatedInput[1] by commas,
                 * sort by descending using Collections lib,
                 * iterate to retrieve the index to be marked &
                 * set Status of Task to undone
                 */
                ArrayList<Integer> multipleIndexList = convertAndSortDescending(seperatedInput[1].split(","));
                for(int i = 0; i < multipleIndexList.size(); i++) {
                    task.unmarkTask(multipleIndexList.get(i)-1);
                }
            }
            else if (commandName.equalsIgnoreCase("delete")) {

                /**
                 * split seperatedInput[1] by commas,
                 * sort by descending using Collections lib,
                 * iterate to retrieve the index to be marked &
                 * delete them
                 */
                ArrayList<Integer> multipleIndexList = convertAndSortDescending(seperatedInput[1].split(","));
                for(int i = 0; i < multipleIndexList.size(); i++) {
                    task.deleteTask(multipleIndexList.get(i)-1);
                    ui.printEmptyLine();
                }
            }
            else if (commandName.equalsIgnoreCase("deadline")) {
                /**
                 * Using indexOf method to extract description & dateline
                 * nextSeparated array will store value in such index
                 * nextSeparated[0] = description;
                 * nextSeparated[1] = deadline;
                 */
                String [] nextSeparated = seperatedInput[1].split("/by");

                Deadline newDeadline = new Deadline(nextSeparated[0].trim(), DateValidator.convertStringToDate(nextSeparated[1].trim()));
                task.addNewTask(newDeadline);
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

                Event newEvent = new Event(nextSeparated[0].trim(), DateValidator.convertStringToDate(separatedTiming[0].trim()), DateValidator.convertStringToDate(separatedTiming[1].trim()));
                task.addNewTask(newEvent);
            }
            else if(commandName.equalsIgnoreCase("todo")) {

                Todo newTodo = new Todo(seperatedInput[1].trim());
                task.addNewTask(newTodo);
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
            isByeAttribute = true;
            ui.showByeMeg();
        }
    }

    /**
     *  This method returns the current status of isByeAttribute
     *  @return TRUE if it is bye; FALSE if it is not bye
     */
    public boolean isItBye() {
        return isByeAttribute;
    }

}
