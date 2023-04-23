package duke;

import duke.exceptions.DukeException;
import duke.util.TaskList;
import duke.ui.Ui;
import duke.command.Command;
import duke.util.Storage;
import duke.util.Parser;

/**
 * The Duke class is the main entry point for the Duke chatbot
 * It is responsible for initializing the UI, Storage and TaskList objects,
 * and running the chatbot loop until an exit command is given
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    /**
     * Constructs a new Duke object with the specified file path for storage.
     * @param filePath the file path for storage
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot loop until an exit command is given.
     */
    public void run() {
        System.out.println(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine());
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }

    /**
     * The main method that creates a new Duke object and starts the chatbot.
     * @param args he command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.sql").run();
    }
}
