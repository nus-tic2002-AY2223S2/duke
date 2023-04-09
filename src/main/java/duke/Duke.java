/**
*  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
*/

package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {

    /********************************************/
    private Ui ui;
    private TaskList tasks;

    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isItBye = false;
        while (!isItBye) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDividerLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isItBye = c.isItBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
