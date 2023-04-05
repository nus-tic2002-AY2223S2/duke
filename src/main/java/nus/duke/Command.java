package nus.duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *  A class representing a command that can be executed by the task list class.
 *  those execute command include list the task, mark the task as completed , unmark,
 *  add the task, delete the task, search for the specific task and display the statistic.
 */
public class Command {

    protected Parser.taskEnum task;
    protected String taskDescription;
    protected Boolean isExit = false;
    protected int taskNumber;
    protected LocalDateTime taskDeadline;
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected String findItem;
    protected String statItem;

    public boolean isExit() {
        return this.isExit;
    }

    public void displayAddTask(TaskList tl, Ui ui) {
        System.out.println("Got it. I've added this task:\n    "
                + ui.displayCommand() +
                "\nNow you have " + tl.getSize() + " tasks in the list.");
    }

    public void displayDeleteTask(TaskList tl) {
        System.out.println("Noted. I've removed this task:\n    "
                + tl.get(taskNumber - 1).toString() +
                "\nNow you have " + (tl.getSize() - 1) + " tasks in the list.");
    }

    public void displayMarkTask(TaskList tl) {
        System.out.println("Noted. I've " + Parser.wordsInDescription[0] + " task " + taskNumber);
    }

    public void unMarkCommand(TaskList tl) throws InvalidCodeException {
        if (taskNumber == 0) {
            //System.out.println("There is no task 0.");
            throw new InvalidCodeException("Invalid code exception");
        }
        try {
            tl.get(taskNumber - 1).markAsNotDone();
            displayMarkTask(tl);
        } catch (IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
            System.out.println("Please give a valid task number that you want to unmark!");
        }
    }

    public void markCommand(TaskList tl) throws InvalidCodeException {
        // ***************************
        // level 3 mark/unmark feature
        // ***************************

        //assert taskNumber != 0 : "Task cannot be null";
        if (this.taskNumber == 0) {
            //System.out.println("There is no task 0.");
            throw new InvalidCodeException("Invalid code exception");
        }

        try {
            tl.get(this.taskNumber - 1).markAsDone();
            displayMarkTask(tl);
        } catch (IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
            System.out.println("Please give a valid task number that you want to mark!");
        }
    }

    // ********************************************
    // level 9 Add Individual Feature: C-Statistics
    // ********************************************
    public void displayStatistic(TaskList tl) {
        System.out.println("Please select the number to get different Statistics:\n1. Stats for Tasks done.\n2. Stats for Tasks not done.\n" +
                "3. Stats for Todo.\n4. Stats for Deadline.\n5. Stats for Events.\n");
        Scanner in = new Scanner(System.in);
        this.statItem = in.nextLine();
        if (Integer.parseInt(statItem) == 1) {
            int stats = 0;
            for (int i = 1; i <= tl.getSize(); i++) {
                if (TaskList.tasksArray.get(i - 1).isDone) {
                    stats++;
                }
            }
            System.out.println("Number of Tasks done = " + stats);
        } else if (Integer.parseInt(statItem) == 2) {
            int stats = 0;
            for (int i = 1; i <= tl.getSize(); i++) {
                if (!TaskList.tasksArray.get(i - 1).isDone) {
                    stats++;
                }
            }
            System.out.println("Number of Tasks not done = " + stats);
        } else if (Integer.parseInt(statItem) == 3) {
            int stats = 0;
            for (int i = 1; i <= tl.getSize(); i++) {
                if (TaskList.tasksArray.get(i - 1) instanceof ToDo) {
                    stats++;
                }
            }
            System.out.println("Number of Todo Task = " + stats);
        } else if (Integer.parseInt(statItem) == 4) {
            int stats = 0;
            for (int i = 1; i <= tl.getSize(); i++) {
                if (TaskList.tasksArray.get(i - 1) instanceof Deadline) {
                    stats++;
                }
            }
            System.out.println("Number of Deadline Task = " + stats);
        } else if (Integer.parseInt(statItem) == 5) {
            int stats = 0;
            for (int i = 1; i <= tl.getSize(); i++) {
                if (TaskList.tasksArray.get(i - 1) instanceof Event) {
                    stats++;
                }
            }
            System.out.println("Number of Event Task = " + stats);
        }
    }

    // **************************************************
    // level 10 Add Individual Feature: C-DetectDuplicate
    // **************************************************
    public void detectDuplicates(TaskList tl) {
        for (int i = 0; i < tl.getSize() - 1; i++ ) {
            if (tl.get(i).description.equals(tl.get(tl.getSize() - 1).description)) {
                System.out.println("\nImportant message: Duplicate task detected! Are you sure you want to add this task? (Y/N)" );
                Scanner in = new Scanner(System.in);
                String answer = in.nextLine().toUpperCase();
                if (answer.equals("N")) {
                    tl.remove(tl.getSize()-1);
                    System.out.println("Duplicate task is removed!");
                } else {
                    System.out.println("Duplicate task is added!");
                }
                break;
            }
        }
    }

    /**
     * Executes the command.
     * @param tl the list of task in a taskList
     * @param ui the user interface for the taskList application
     * @param storage the storage file for the taskList data
     * @throws DukeException the command object corresponding to the user input
     * @throws IOException if there is an error accessing the storage file
     * @throws InvalidCodeException if the relevant attribute equal to null
     */

    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException, IOException, InvalidCodeException {


        //assert task != null : "Task cannot be null";
        if (task == null) {
            throw new InvalidCodeException("Invalid code exception");
        }
        switch (task) {
            // *********************
            // level 1 bye feature
            // *********************
            case BYE:
                this.isExit = true;
                break;
            // **********************
            // level 2 list feature
            // **********************
            case LIST:
                for (int i = 1; i < tl.getSize() + 1; i++) {
                    System.out.println(i + "." + TaskList.tasksArray.get(i - 1).toString());
                }
                break;

            case MARK:
                markCommand(tl);
                break;

            case UNMARK:
                unMarkCommand(tl);
                break;

            // **********************
            // level 4 To-do feature
            // **********************
            case TODO:

                if (taskDescription == null) {
                    throw new InvalidCodeException("Invalid code exception");
                }

                tl.add(new ToDo(taskDescription));
                //tl.testAddTask();
                displayAddTask(tl, ui);
                break;

            // *************************
            // level 4 Deadline feature
            // *************************
            case DEADLINE:
                if (taskDescription == null || taskDeadline == null) {
                    throw new InvalidCodeException("Invalid code exception");
                }
                //assert !taskDeadline.isBefore(LocalDateTime.now()) : "Due date cannot be in the past";
                if (!taskDeadline.isBefore(LocalDateTime.now())) {
                    tl.add(new Deadline(taskDescription, taskDeadline));
                    displayAddTask(tl, ui);
                } else {
                    System.out.println("deadline shouldn't earlier than now");
                }
                break;

            // *************************
            // level 4 Event feature
            // *************************
            case EVENT:
                if (taskDescription == null || start == null || end == null) {
                    throw new InvalidCodeException("Invalid code exception");
                }
                tl.add(new Event(taskDescription, start, end));
                displayAddTask(tl, ui);
                break;

            // *************************
            // level 5 Delete feature
            // *************************
            case DELETE:
                if (taskNumber == 0) {
                    throw new InvalidCodeException("Invalid code exception");
                }
                try {
                    displayDeleteTask(tl);
                    tl.remove(taskNumber - 1);
                } catch (IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
                    System.out.println("Please give a valid task number that you want to delete!");
                }
                break;

            // *************************
            // level 9 Find feature
            // *************************
            case FIND:
                if (findItem != null) {
                    System.out.println("Here are the matching tasks in your list:");
                    for (int i = 1; i <= tl.getSize(); i++) {
                        if (TaskList.tasksArray.get(i - 1).description.contains(findItem)) {
                            System.out.println(i + "." + TaskList.tasksArray.get(i - 1).toString());
                        }
                    }
                }
                break;

            case STAT:
                displayStatistic(tl);
                break;

            default:
        }
    }
}
