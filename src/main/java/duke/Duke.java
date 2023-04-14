package duke;

import duke.Exception.DukeException;
import duke.Ui.Ui;
import duke.Command.Command;


public class Duke {
    private final Ui ui;

    public Duke() {
        ui = new Ui();
    }

    /**
     * Calls the UI class to display welcome message
     * Calls the execute() method in Command class to begin the program
     */
    public void run() throws DukeException {
        ui.showWelcome();
        Command.execute();
    }

    /**
     * Calls the run() method
     * @param args takes in array of arguments
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}