package duke;

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
        ui.getUserInput(tasks);
    }

    public static void main(String[] args) {
        new Duke().run();

    }







}
