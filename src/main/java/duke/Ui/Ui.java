package duke.Ui;
public class Ui {
    public Ui() {
        //empty constructor
    }

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke, your task scheduler.");
        Ui.showLine();
        System.out.println("What can I do for you?\n");
        System.out.println("Type "+ "help" + " to see the available commands");
    }

    /**
     * Prints a line for demarcation of output texts
     */
    public static void showLine() {
        System.out.println("__________________________________________________________________________________________");
    }

    public static void showCommands() {
        showLine();
        System.out.println("Here are the commands you can use:");
        showLine();
        System.out.println("1. list - list out your current task(s).");
        System.out.println("2. todo (description) - adds a Todo task");
        System.out.println("3. deadline (description) /by (yyyy-MM-dd) - adds a Deadline task");
        System.out.println("4. event (description) /from (yyyy-MM-dd) /to (yyyy-MM-dd) - adds an Event task");
        System.out.println("5. delete (index) - deletes a specific task");
        System.out.println("6. mark (index) - marks a task as done");
        System.out.println("7. unmark (index) - marks a task as not done");
        System.out.println("8. priority (index) (high/low/medium) - changes the priority level of a specific task");
        System.out.println("9. find (keyword) - list out the tasks that contain the keyword");
        System.out.println("10. help - list out all the usable commands");
        System.out.println("11. bye - exit the program");
        showLine();
    }
}
