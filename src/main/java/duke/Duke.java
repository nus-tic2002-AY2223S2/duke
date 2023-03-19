package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke().run();

    }

}
