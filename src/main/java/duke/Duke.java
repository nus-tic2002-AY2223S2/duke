package duke;

import duke.Ui.Ui;
import duke.Command.Command;


public class Duke {
    private final Ui ui;

    public Duke() {
        ui = new Ui();
    }
    public void run() {
        ui.showWelcome();
        Command.execute();
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
