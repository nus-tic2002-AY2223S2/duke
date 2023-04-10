package duke.Ui;
public class Ui
{
    public Ui() {
        //empty constructor
    }

    /**
     * This method is called upon once the program is executed
     * to display the welcome page
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke, your task scheduler.");
        System.out.println("What can I do for you?\n");
    }

    /**
     * This method is called to print lines for demarcation of output texts
     */
    public static void showLine() {
        System.out.println("________________________________________________________________________");
    }
}
