package ui;

import java.util.Scanner;
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
        System.out.println("Please key in command followed as below:");
        String LIST_ALL_TASKS = "1. List all existing tasks, enter list\n";
        String TODO_TASK = "2. Add a todo task, enter todo XXX\n";
        String DEADLINE_TASK = "3. Add a deadline task, enter deadline XXX /by \"yyyy-MM-dd HH:mm\"\n";
        String EVENT_TASK = "4. Add a event task, enter event XXX /from \"yyyy-MM-dd HH:mm\" /to \"yyyy-MM-dd HH:mm\"\n";
        String MARK_TASK = "5. Mark task, enter mark [task index]\n";
        String UNMARK_TASK = "6. Unmark task, enter unmark [task index]\n";
        String DELETE_TASK = "7. Delete task, enter delete [task index]\n";
        String SCHEDULE_TASK = "8. View the schedule for a specific date, enter schedule \"yyyy-MM-dd HH:mm\"\n";
        String SAVE_TASK = "9. Save task, enter save\n";
        String EXIT = "10. To exit Duke, enter bye\n";
        System.out.println(LIST_ALL_TASKS + TODO_TASK + DEADLINE_TASK + EVENT_TASK
                + MARK_TASK + UNMARK_TASK + DELETE_TASK + SCHEDULE_TASK + SAVE_TASK + EXIT);

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
