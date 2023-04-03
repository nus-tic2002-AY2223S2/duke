package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import task.Task;



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

    //store tasks into an array START

    //store tasks into an array END
    //messages START
    public static void help(){
        System.out.println("Hi, it looks like you did not type anything.");
        System.out.println("You may try the following commands:");
        System.out.println("Enter List -> to list all the tasks");
        System.out.println("Enter Bye -> to end the conversation");
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
