import Duke.command.Command;
import Duke.dukeexception.DukeException;
import Duke.storage.Storage;
import Duke.parser.Parser;
import Duke.tasklist.Tasklist;
import Duke.ui.UI;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private Tasklist tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new Tasklist(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        } catch (FileNotFoundException e) {
            tasks = new Tasklist();
        }
    }

    public void run() {
        ui.showWelcome();
        tasks = new Tasklist();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * "data/tasks.txt" is an optional argument when executing the program
     * Duke will create the folder when it does not exist
     * @param args
     */
    public static void main(String[] args) {
        new Duke("Desktop/data/tasks.txt").run();
    }
}