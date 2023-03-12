package Task;

import java.util.ArrayList;
import java.util.Scanner;
import Exception.DukeException;
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
        System.out.println(space + "Hello! I'm Duke.Duke\n" + space +"What can I do for you?");
        Ui.showLine();
    }
    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showLine(){
        System.out.println("____________________________________________________________");
    }
    public static void printList(ArrayList<Task> lists) {
        String space = " ";
        Ui.showLine();
        System.out.println(space + "Here are the tasks in your list:");
        int taskNum = 0;
        for(Task list : lists){
            taskNum ++;
            System.out.println(space + taskNum + "." + list.toString());
        }
        Ui.showLine();
    }

    public static void Echo(String s) {
        String space = " ";
        Ui.showLine();
        System.out.println(space + s);
        Ui.showLine();
    }

    public static void showError(DukeException e){
        Echo(e.getMessage());
    }

}
