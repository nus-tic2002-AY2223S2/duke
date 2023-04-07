package duke;

import java.util.Scanner;
import java.util.ArrayList;
import duke.Ui.Ui;
import duke.Command.Command;
//import duke.Storage;

public class Duke {
    private final Ui ui;
    //private TaskList tasks;
    //private final Storage storage;

    public Duke() {
        ui = new Ui();
       //storage = new Storage();
    }
    public void run() {
        ui.showWelcome();
        Command.execute();
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
