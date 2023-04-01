package ui;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import exception.DukeException;
public class Ui {
    public static void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String space = " ";
        Ui.showLine();
        System.out.println(space + "Hello! I'm Duke\n" + space +"What can I do for you?");
        Ui.showLine();
    }

    /**
     * Show to the user about Duke menu
     */
    public static void showUserMenu(){
        Set<String> hashSet = new HashSet<>();
        String space = " ";
        System.out.println("Please key in command followed as below:");
        String LIST_ALL_TASKS = "List all existing tasks, enter list\n";
        String TODO_TASK = "Add a todo task, enter todo XXX\n";
        String DEADLINE_TASK = "Add a deadline task, enter deadline XXX /by \"yyyy-MM-dd HH:mm\"\n";
        String EVENT_TASK = "Add a event task, enter event XXX /from \"yyyy-MM-dd HH:mm\" /to \"yyyy-MM-dd HH:mm\"\n";
        String MARK_TASK = "Mark task, enter mark [task index]\n";
        String UNMARK_TASK = "Unmark task, enter unmark [task index]\n";
        String DELETE_TASK = "Delete task, enter delete [task index]\n";
        String FIND_TASK = "Find task, enter find XXX\n";
        String SCHEDULE_TASK = "View the schedule for a specific date, enter schedule \"yyyy-MM-dd HH:mm\"\n";
        String SAVE_TASK = "Save task, enter save\n";
        String EXIT = "To exit Duke, enter bye\n";
        hashSet.add(LIST_ALL_TASKS);
        hashSet.add(TODO_TASK);
        hashSet.add(DEADLINE_TASK);
        hashSet.add(EVENT_TASK);
        hashSet.add(MARK_TASK);
        hashSet.add(UNMARK_TASK);
        hashSet.add(DELETE_TASK);
        hashSet.add(FIND_TASK);
        hashSet.add(SCHEDULE_TASK);
        hashSet.add(SAVE_TASK);
        hashSet.add(EXIT);
        for(String s: hashSet){
            System.out.print(space + s);
        }
        System.out.println();
    }


    /**
     * User key in full command
     *
     * @return full command
     */
    public static String getUserCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showLine(){
        System.out.println("____________________________________________________________");
    }

    /**
     * If user just want to show one sentence, can use this echo method
     *
     * @param s string for print
     */
    public static void Echo(String s) {
        String space = " ";
        Ui.showLine();
        System.out.println(space + s);
        Ui.showLine();
    }

    /**
     * @param e print the exception message
     */
    public static void showError(DukeException e){
        Ui.Echo(e.getMessage());
    }

}
