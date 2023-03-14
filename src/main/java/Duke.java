import java.io.File;
import java.util.List;

/**
 * @author Ng Kwang Hai Jeffrey
 * @version Level 7.5
 */
public class Duke {
    private Storage storage;
    private List<Task> tasks;
    private Ui ui;

    public Duke(String filename) throws DukeException {
        ui = new Ui();

        File directory = new File("data");
        if (!directory.exists()) {
            System.out.println("Directory doesn't exist, creating it now");
          boolean isCreated =  directory.mkdir();
          if (isCreated){
              System.out.println("Directory created");
          }else {
              throw new DukeException("OOPS! Failed to create directory, please check your folder permission!");
          }
        }
        storage = new Storage(filename);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();

        Parser parser = new Parser();
        String userInput = "";
        Task t;
        TaskList tasks = new TaskList();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(e);
            ui.showLoadingError();
        }

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
                System.out.println(e);
            } finally {
                ui.showLine();
                //Save the task into the file.
                storage.save(tasks);

            }
        }
    }
}
