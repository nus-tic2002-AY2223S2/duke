package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.io.File;

/**
 * @author Ng Kwang Hai Jeffrey (A0227137H)
 * Implemented individual feature 1: C-duke.task.Priority
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The duke.Duke class represents the main component of the duke.Duke application.
     *
     * @param filename the name of the file to store the data.
     * @throws DukeException if there is an error creating the directory or initializing the storage.
     */
    public Duke(String filename) throws DukeException {
        ui = new Ui();
        File directory = new File("data"); //The directory of the save file.
        if (!directory.exists()) {
            System.out.println("Directory doesn't exist, creating it now");
            boolean isDirCreated = directory.mkdir();
            if (isDirCreated) {
                System.out.println("Directory created!");
            } else {
                throw new DukeException("☹ OOPS!!! Failed to create directory, please check your folder permission!");
            }
        }
        storage = new Storage(filename);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

    /**
     * The run method handles the main functionality of the duke.Duke application.
     * It reads user input and performs the actions based on the command entered by the user.
     */
    public void run() {
        Parser parser = new Parser();
        String userInput = "";
        Task t;
        tasks = new TaskList();

        //attempt to load data from save file.
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
        }

        ui.showWelcome(); //Display logo

        while (!userInput.equals("bye")) {
            //Read user input.
            userInput = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")

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
                    //Create duke.task.Deadline task
                    t = parser.createDeadline(userInput);
                    tasks.addTask(t);
                } else if (parser.isEventCommand(userInput)) {
                    //Create event task
                    t = parser.createEvent(userInput);
                    tasks.addTask(t);
                } else if (parser.isFindCommand(userInput)) {
                    //List out all the elements based on the user's find keyword.
                    tasks.listFindTasks(userInput);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
                //Save the task into the file.
                storage.save(tasks);
            }
        }
    }
}
