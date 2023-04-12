package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import task.Task;

/**
 * ui class
 * -> other important commands and takes in the command
 */

public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    //Start of chatbot
    private static String welcome = "Hello! I'm Duke, the chatbot! What can I do for you?";
    private static Scanner in;

    //Greeting
    public static void greeting() {
        System.out.println(logo);
        System.out.println(welcome);
    }

    //take in inputs/commands
    public static String input() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim().toLowerCase();

        return command;
    }

    //messages START
    public static void help(){
        System.out.println("Hi, do you need any help @_@");
        System.out.println("You may try the following commands:");
        System.out.println("Enter List -> to list all the tasks");
        System.out.println("To mark a task as complete -> mark (task num)");
        System.out.println("To mark a task as incomplete -> unmark (task num)");
        System.out.println("To remove a task -> delete (task num)");
        System.out.println("To add todo -> todo (description)");
        System.out.println("To add deadline -> deadline (description) /by (date)");
        System.out.println("To add event -> event (description) /from (date) /to (date)");
        System.out.println("To find something in the list -> find (keyword)");
        System.out.println("To update a task -> update (task num)-(something to replace)-(updated description) ;D works for date too!");
        System.out.println("To add a recurring task -> repeat (task num)");
        System.out.println("Enter Bye -> to end the conversation");
        System.out.println("Hope this helps ^_^");
    }
    public static void addedTaskMsg(){
        System.out.println("Got it. I've add this task:");
    }

    public static void showLoadingError() {
        System.out.println("ERROR loading -_-");
    }

    public static void lists(ArrayList<Task> listOfTasks){
        int listNo=0;
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            ++listNo;
            System.out.println(listNo + ". " + listOfTasks.get(i).toString());
        }
    }

    public static void printMsg(String message) {
        System.out.println(message);
    }

    public static void showLine(){
        System.out.println("------------------------");
    }

    //messages END

}
