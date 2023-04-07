package duke.Ui;
public class Ui
{
    public Ui()
    {
        /**
        *empty constructor
        */
    }
    public static void showWelcome()
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?\n");
    }

    public static void showLine()
    {
        System.out.println("__________________________________________");
    }
}
