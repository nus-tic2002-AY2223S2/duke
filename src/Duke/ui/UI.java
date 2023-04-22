package Duke.ui;

import java.util.Scanner;

public class UI {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private String commandlist = "Here are the commands you can type: \n"
            + "1. Add Todo Task [e.g. todo read a book]\n"
            + "2. Add Deadline Task [e.g. deadline submit project /by 11 March 2022 2359]\n"
            + "3. Add Event [e.g. event attend class /at 2 March 2022 7pm]\n"
            + "4. To check what is in the list [e.g. list]\n"
            + "5. To mark task as done [e.g. mark 1]\n"
            + "6. To unmark task as done [e.g. unmark 1]\n"
            + "7. Set Priority for task [e.g. priority 1]\n"
            + "8. Remove Priority for task [e.g. remove-priority 1]\n"
            + "9. To save list [e.g. save]\n"
            + "10. To delete task [e.g. delete 1]\n"
            + "11. To end program [e.g. bye]\n";;

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________\n");
        System.out.println("Hello I'm Duke\nWhat can I help you with?");
        System.out.println("________________________________\n");
        System.out.println(commandlist);
    }

    public void showLine() {
        System.out.println("________________________________");
    }

    public String readCommand() {
        System.out.println("Insert Command here: ");
        Scanner in = new Scanner(System.in);
        String command;
        command = in.nextLine();

        return command;
    }

    public void showError(String errorMsg) {
        System.err.println("Please try again!");
    }

    public void showLoadingError() {
        System.err.println("Please try again");
    }
}