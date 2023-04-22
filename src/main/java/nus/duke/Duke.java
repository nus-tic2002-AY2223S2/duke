package nus.duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.io.File;

public class Duke{

    public static String filePath;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) throws DukeException {

        Duke.filePath = filePath;
        File directory = new File("data");
        if (!directory.exists()) {
            System.out.println(" No such directory!");
            boolean isDirCreated = directory.mkdir();
            if (isDirCreated) {
                System.out.println("Directory already created");
            } else {
                throw new DukeException("unable to create directory!");
            }
        }


        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        } catch (ParseException e) {

        }
    }

    public void run() {
        ui.showWelcome();
        // *********************
        // level 1 bye feature
        // *********************
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                c.detectDuplicates(tasks);
                Storage.save(tasks, filePath);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidCodeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

}

