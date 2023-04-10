package duke;

import duke.Ui.Ui;
import duke.Command.Command;


public class Duke {
    private final Ui ui;

    public Duke() {
        ui = new Ui();
    }

    /**
     * The run method calles the UI class to display
     * calls the execute() method in Command class to begin the program
     */
    public void run() throws DukeException {
        ui.showWelcome();
        Command.execute();
    }

    /**
     * The main function of the program, calls the run() method
     * @param args takes in array of arguments
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
