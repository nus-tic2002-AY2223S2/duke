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
        System.out.println("What can I do for you?");
        this.showDividerLine();
        System.out.println("\n");
    }

    /**
     *  this method will print out a divider line
     */
    public void showDividerLine() {
        System.out.println(uiDivider);
    }

    /**
     *  this method will print out a loading error
     */
    public void showLoadingError() {
        System.out.println(loadingError);
    }

    /**
     *  this method will print motherhood bye statement
     */
    public void showByeMeg() {
        this.showDividerLine();
        System.out.println(byeMsg);
    }

    /**
     *  this method will take in an input from the user and returns it
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
        System.out.println("Error caught:" + errorMessage);
    }
}
