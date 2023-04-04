package nus.duke;

import java.util.Scanner;

public class Ui {
    protected String sentences;

    public Ui() {

    }

    public String showLoadingError() {
        return "Cannot load the file.";
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String newlogo = " __        __   _____\n"
            + "|  |  __  |  | |  __ \\ \n"
            + "\\  \\_/  \\_/  / | |__| | \n"
            + " \\    __    /  |  ___/\n"
            + "  \\__/  \\__/   |_|      creation®\n";

        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + newlogo);
        System.out.println("Hello. This is To-do list/Reminder created by WP.\n" +
                "Features: list, mark, unmark, todo, deadline, event, delete, find, stat, bye\n" +
                "What can i do for you?\n");
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        this.sentences = in.nextLine();
        return sentences;
    }

    public String displayCommand() {
        String display = "";
        for(int i = 1; i < Parser.wordsInDescription.length; i++) {
            display += Parser.wordsInDescription[i] + " ";
        }
        return display;
    }

    public void showLine() {
        System.out.println("\n_________________________________________\n");
    }

    public void showError(String message) {

    }
}

