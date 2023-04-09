package duke.Command;

import duke.*;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.TasksType.Deadline;
import duke.TasksType.Event;
import duke.TasksType.Task;
import duke.TasksType.Todo;
import duke.Utility.Util;

import java.util.Scanner;

public class Command {
    /**
     * This executeLoad() method loads the previously saved file, with each line into the different task types
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
     */
    public static void execute() {
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

            if (question.equalsIgnoreCase("list")) { //loop to print out the items in list[]
                list.listTask();
                continue;
            }

            try {
                Parser.validateQuestion(question, list.getList());
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
                        System.out.println("Item " + (num + 1) + " is not done.");
                        loader.saveToFile(list.getList());
                        continue;
                    } else {
                        list.getTask(num).markAsDone();
                        System.out.println("Item " + (num + 1) + " is done.");
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
                    Deadline dl = new Deadline(deadlineSplit[0].trim(), Util.convertDateTime(deadlineSplit[1].trim()));
                    list.addTask(dl);
                }

                //catch for "event"
                else if (splitted[0].equalsIgnoreCase("event")) {
                    String[] eventSplit = splitted[1].split("/from");
                    String[] eventSplitAgain = eventSplit[1].split("/to");
                    Event ev = new Event(eventSplit[0].trim(), Util.convertDateTime(eventSplitAgain[0].trim()), Util.convertDateTime(eventSplitAgain[1].trim()));
                    list.addTask(ev);
                }
                //catch for delete
                else if (splitted[0].equalsIgnoreCase("delete")) {
                    Task toDelete = list.getTask(Integer.parseInt(splitted[1]) - 1);
                    list.deleteTask(Integer.parseInt(splitted[1]) - 1);
                }
                //catch for priority
                else if (splitted[0].equalsIgnoreCase("priority")) {
                    String[] prioritySplit = splitted[1].split(" ");
                    list.setPriority(Integer.parseInt(prioritySplit[0]) - 1, Util.stringToLevel(prioritySplit[1]));
                }
                else if (splitted[0].equalsIgnoreCase("find")) {
                    list.find(splitted[1]);
                }
                else {
                    System.out.println("duke.TasksType.Task not recognised");
                }
            }
            list.sortList();
            loader.saveToFile(list.getList()); //saves the list into data/saved.txt
        }
    }
}
