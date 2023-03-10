import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private List<Task> tasks;
    public Duke(String filename){

        File directory = new File("data");
        if (!directory.exists()) {
            System.out.println("Directory doesn't exist, creating it now");
            directory.mkdir();
        }
        storage = new Storage(filename);
    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, I'm Duke. What can i do for you?");

        Parser parser = new Parser();
        String userInput = "";
        Task t;
        TaskList tasks = new TaskList();
        while (!userInput.equals("bye")) {
            //Read user input.
            userInput = sc.nextLine();
            try {
                //Exit the chatbot.
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");

                } else if (parser.isListCommand(userInput)) {
                    //List out all the elements stored.
                    tasks.listTasks();
                } else if (parser.isDeleteCommand(userInput)) {
                    //Deletes a task.
                    tasks.updateTask(userInput);
                } else if (parser.isMarkCommand(userInput)) {
                    //Marks a task as done.
                    tasks.updateTask(userInput);
                } else if (parser.isUnmarkCommand(userInput)) {
                    //unmarks a task as not done.
                    tasks.updateTask(userInput);
                } else if (parser.isToDoCommand(userInput)) {
                    //Create to-do task
                    t = parser.createToDo(userInput);
                    tasks.addTask(t);

                } else if (parser.isDeadlineCommand(userInput)) {
                    //Create Deadline task
                    t = parser.createDeadline(userInput);
                    tasks.addTask(t);

                } else if (parser.isEventCommand(userInput)) {
                    //Create event task
                    t = parser.createEvent(userInput);
                    tasks.addTask(t);

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {

            }
            //Save the task into the file.
            storage.save(tasks);
        }
    }


    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }


}
