package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.DukeFileReaderAndWriter;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private final DukeFileReaderAndWriter fileReaderAndWriter;
    private final TaskList tasks;
    private final Ui ui;

    public Duke() {
        ui = new Ui();
        fileReaderAndWriter = new DukeFileReaderAndWriter();
        tasks = new TaskList(fileReaderAndWriter.load());
    }

    public static void main(String[] args) {
        new Duke().run();

    }

    public void run() {
        ui.welcome();

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, fileReaderAndWriter);
            isExit = command.isExit();
        }
    }

}
