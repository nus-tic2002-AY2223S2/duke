package duke.util;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello, I'm Duke. What can i do for you?");
    }

    public void showLine() {
        System.out.println("----------------------------------------------");
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! Error loading save file, please check if the file is in the correct path");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
