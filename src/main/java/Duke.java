//import duke.exception.DukeException;

import exception.DukeException;
import ui.Ui;
import task.TaskList;
import storage.Storage;
import command.*;
import parser.Parser;
import java.io.IOException;


//DUKE
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    //DUKE

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run()  {
        ui.greeting();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.input();
            ui.showLine();
            Commands c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            storage.saveToFile(tasks.getTasks());
            isExit = c.isExit();
        }
        Ui.showLine();

    }

    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
    }
}
