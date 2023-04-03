/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke;

public class Ui {
    /**
     *  Attribute
     */
    private static String uiDivider = "____________________________________________________________";

    /**
     *  Constructor
     */
    public Ui() {

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

        this.showLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        this.showLine();
        System.out.println("\n");
    }

    public static void showLine() {
        System.out.println(uiDivider);

    }
}
