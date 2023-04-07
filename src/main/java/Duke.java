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
        //if (storage.exists() ) {
            tasks = new TaskList(storage.load());
        /*} else {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }

    public void run()  {
        ui.greeting();
        boolean isExit = false;

        while (!isExit) {
            //try {
                String fullCommand = ui.input();
                ui.printMsg("----------------------"); // show the divider line
                Commands c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.saveToFile(tasks.getTasks());
                isExit = c.isExit();
           /* } catch (IOException e ){
                ui.printMsg("ERROR");
                continue;
            } finally {
                ui.printMsg("----------------------");
            }*/
        }

    }

    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
    }
}
