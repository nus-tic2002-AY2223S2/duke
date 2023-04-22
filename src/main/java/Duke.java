import java.io.FileNotFoundException;
import exception.DukeException;
import ui.Ui;
import storage.Storage;
import parser.Parser;
import tasklist.TaskList;
import task.Task;
import command.Command;

import java.io.File;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            TaskList loadedTasks = storage.loadTasks();
            for (Task task : loadedTasks) {
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            ui.showError("Error: Unable to load tasks from the file.");
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data" + File.separator + "duke.txt").run();
    }
}