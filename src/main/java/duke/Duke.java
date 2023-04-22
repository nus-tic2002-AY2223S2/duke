package duke;

import duke.exceptions.DukeException;
import duke.util.TaskList;
import duke.ui.Ui;
import duke.command.Command;
import duke.util.Storage;
import duke.util.Parser;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError());;
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) {
        new Duke("data/duke.sql").run();
    }
}
