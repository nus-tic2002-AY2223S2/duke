/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke;

import java.util.Scanner;

public class Ui {
    /**
     *  Attribute
     */
    private static String loadingError = "No file found";
    private static String uiDivider = "____________________________________________________________";
    private static String byeMsg = "Bye. Hope to see you again soon";
    private static Scanner scanObj;
    private static String input;

    /**
     *  Constructor
     */
    public Ui() {
        scanObj = new Scanner(System.in);
    }

    /**
     *  this method will show the initial welcome message upon starting the app
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        this.showDividerLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("Here's the possible instructions as follows:");
        System.out.println(" Add Task [Please follow format] -> Add new Task into task list");
        System.out.println(" -> Todo e.g.: 'todo <description>' ");
        System.out.println(" -> Dateline e.g.: 'deadline <description> /by <deadline>' ");
        System.out.println(" -> Event e.g.: 'event <description> /from <start date> /to <end date>' ");
        System.out.println(" -> date format in terms of 'yyyy-mm-dd HH:mm' ");
        System.out.println(" Mark / Unmark / Delete Task; [insert comma between numbers if want to perform Mass Operations]");
        System.out.println(" List -> List all Tasks ");
        System.out.println(" Bye -> Exit");

        this.showDividerLine();
        System.out.println("What can I do for you?");
        this.showDividerLine();
    }

    /**
     *  this method will print out a divider line
     */
    public void showDividerLine() {
        System.out.println(uiDivider);
    }
    /**
     *  this method will print out next line
     */
    public void printEmptyLine() {
        System.out.println();
    }


    /**
     *  this method will print out a loading error
     */
    public void showLoadingError(String msg) {
        System.out.println(loadingError + "; " + msg);
    }

    /**
     *  this method will print motherhood bye statement
     */
    public void showByeMeg() {
        System.out.println(byeMsg);
    }

    /**
     *  this method will take in an input command from the user and returns it
     */
    public String readCommand()
    {
        input = scanObj.nextLine();
        return input;
    }

    /**
     *  this method will take in an error and display it out
     */
    public void showError(String errorMessage)
    {
        System.out.println("Error caught: " + errorMessage);
    }
}
