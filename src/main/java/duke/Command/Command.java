package duke.Command;

import duke.Exception.DukeException;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.TasksType.Deadline;
import duke.TasksType.Event;
import duke.TasksType.Task;
import duke.TasksType.Todo;
import duke.Ui.Ui;
import duke.Utility.Util;
import java.util.Scanner;

public class Command {
    /**
     * This executeLoad() method loads the previously saved file, with each line into the different task types
     * Checks for the task type and returns a task element that is used to store in the ArrayList is the loadFile() method
     * Checks if the string has an X to change the isDone member of the task to true
     * Checks the priority level and change the priorityLevel of the task according
     * @param line takes in a line of string input that was loaded from the existing file
     * @return a task element with the correct task type, isDone and priorityLevel for subsequent storing to ArrayList in loadFile() method
     */
    public static Task executeLoad(String line) {
        Task task;
        if (line.contains("[T]")) { //loads Todos
            task = new Todo(line.substring(7, line.indexOf("[Priority ")).trim());
        }
        else if (line.contains("[D]")) { //loads Deadlines
            String description = line.substring(7, line.indexOf("[Priority "));
            String[] deadlineSplit = description.split("/by");
            task = new Deadline(deadlineSplit[0].trim(), Util.convertDateTime(deadlineSplit[1].trim()));
        }
        else {
            String description = line.substring(7, line.indexOf("[Priority ")); //loads Events
            String[] eventSplit = description.split("/from");
            String[] eventSplitAgain = eventSplit[1].split("/to");
            task = new Event(eventSplit[0].trim(), Util.convertDateTime(eventSplitAgain[0].trim()), Util.convertDateTime(eventSplitAgain[1].trim()));
        }
        if (line.contains("[X]")) {
            task.markAsDone();
        }
        if (line.contains("[Priority High")) {
            task.changePriority(Task.priorityLevel.High);
        }
        else if (line.contains("[Priority Low")) {
            task.changePriority(Task.priorityLevel.Low);
        }
        return task;
    }

    /**
     * This execute() method tries to load a file, and runs the endless loop to scan for user inputs (commands)
     * declares the boolean, scanner object, loader, task list
     * calls for load file to try loading an existing file and store into the list
     * starts loop and scans for user commands (question)
     * validates the question by calling the Parser class method validateQuestion(), to see if input is valid
     * checks if mark/unmark commands is valid by calling the checkMark() method in Util
     * stores the tasks according to the user commands and according to the task type, by calling the TaskList class methods
     * sort the ArrayList from priority level high to low, by calling the sortList() method in TaskList class
     * saves the list into text file by calling the saveToFile() in Storage class
     */
    public static void execute() throws DukeException {
        boolean isBye = false;
        Scanner Obj = new Scanner(System.in);
        Storage loader = new Storage();
        TaskList list = new TaskList();
        list.storeList(loader.loadFile());

        while (!isBye) {
            String question = Obj.nextLine();

            if (question.equalsIgnoreCase("bye")) { //if the input is bye then end the program
                isBye = true;
                System.out.println("Bye. Hope to see you again soon!");
                continue;
            }
            //catch for help
            if (question.equalsIgnoreCase("help")) {
                Ui.showCommands();
                continue;
            }
            if (question.equalsIgnoreCase("list")) { //loop to print out the items in list[]
                list.listTask();
                continue;
            }

            try {
                Parser.validateQuestion(question.trim(), list.getList());
            } catch (DukeException error) {
                System.out.println(error);
                continue;
            }
            if (question.contains("mark")) {
                if (Util.checkMark(question)) {
                    String[] splitted = question.split(" ");
                    int num = Integer.parseInt(splitted[1]) - 1;
                    if (question.contains("unmark")) {

                        list.getTask(num).markAsNotDone();
                        System.out.println("Oops! Task " + (num + 1) + " is marked as not done.");
                        System.out.println(list.getTask(num));
                        Ui.showLine();
                        loader.saveToFile(list.getList());
                        continue;
                    } else {
                        list.getTask(num).markAsDone();
                        System.out.println("Well Done! Task " + (num + 1) + " is done.");
                        System.out.println(list.getTask(num));
                        Ui.showLine();
                        loader.saveToFile(list.getList());
                        continue;
                    }
                }
            }

            else {
                String[] splitted = question.split(" ", 2);

                //catch for "to-do"
                if (splitted[0].equalsIgnoreCase("todo")) {
                    Todo td = new Todo(splitted[1].trim());
                    list.addTask(td);
                }

                //catch for "deadline"
                else if (splitted[0].equalsIgnoreCase("deadline")) {
                    String[] deadlineSplit = splitted[1].split("/by");

                    if (Parser.isValidDateTime(deadlineSplit[1].trim())) { //validates the dateTime format from user input
                        try { //validates the deadline dateTime
                            Util.validateDateTime(Util.convertDateTime(deadlineSplit[1].trim()));
                        } catch (DukeException dateError) {
                            System.out.println(dateError);
                            continue;
                        }
                        Deadline dl = new Deadline(deadlineSplit[0].trim(), Util.convertDateTime(deadlineSplit[1].trim()));
                        list.addTask(dl);
                    }
                    else {
                        System.out.println("Invalid date and time format. Please use the format: yyyy-MM-dd HH:mm");
                        continue;
                    }
                }

                //catch for "event"
                else if (splitted[0].equalsIgnoreCase("event")) {
                    String[] eventSplit = splitted[1].split("/from");
                    String[] eventSplitAgain = eventSplit[1].split("/to");

                    if (Parser.isValidDateTime(eventSplitAgain[0].trim()) && //validates the dateTime format
                            Parser.isValidDateTime(eventSplitAgain[1].trim())) {
                        try { //validates the start and end dateTimes
                            Util.validateDateTime(Util.convertDateTime(eventSplitAgain[0].trim()));
                            Util.validateDateTime(Util.convertDateTime(eventSplitAgain[1].trim()));
                        } catch (DukeException dateError) {
                            System.out.println(dateError);
                            continue;
                        }
                        try { //validates the start dateTime should be before the end dateTime
                            Util.validateEventDate(Util.convertDateTime(eventSplitAgain[0].trim()), Util.convertDateTime(eventSplitAgain[1].trim()));
                        } catch (DukeException dateError) {
                            System.out.println(dateError);
                            continue;
                        }
                        Event ev = new Event(eventSplit[0].trim(), Util.convertDateTime(eventSplitAgain[0].trim()), Util.convertDateTime(eventSplitAgain[1].trim()));
                        list.addTask(ev);
                    }
                    else {
                        System.out.println("Invalid date and time format. Please use the format: yyyy-MM-dd HH:mm");
                        continue;
                    }
                }
                //catch for delete
                else if (splitted[0].equalsIgnoreCase("delete")) {
                    list.deleteTask(Integer.parseInt(splitted[1]) - 1);
                }
                //catch for priority
                else if (splitted[0].equalsIgnoreCase("priority")) {
                    String[] prioritySplit = splitted[1].split(" ");
                    try { //validates index to change priority
                        Parser.checkPriority(Integer.parseInt(prioritySplit[0]) - 1, list.getList());
                    } catch (DukeException indexError) {
                        System.out.println(indexError);
                        continue;
                    }
                    list.setPriority(Integer.parseInt(prioritySplit[0]) - 1, Util.stringToLevel(prioritySplit[1]));
                }

                //catch for find
                else if (splitted[0].equalsIgnoreCase("find")) {
                    list.find(splitted[1]);
                }

                else {
                    System.out.println("Input not recognised");
                }
            }
            list.sortList(); //sorts the list of tasks according to the priority level
            loader.saveToFile(list.getList()); //saves the list into data/saved.txt
        }
    }
}