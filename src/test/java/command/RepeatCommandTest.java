package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * RepeatCommandTest
 * -> To test those methods in RepeatCommand
 */

public class RepeatCommandTest {

    @Test
    public void RepeatCommandStringTest() {
        int row = 1;
        RepeatCommand r = new RepeatCommand(row);
        assertEquals(1, r.posNo);
    }

    @Test
    public void RepeatExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        task.taskslist.add(new ToDo("Wash the dishes"));
        task.taskslist.add(new ToDo("Prepare the dishes"));
        int row = 0;
        RepeatCommand r = new RepeatCommand(row);
        r.execute(task, ui, storage);
        assertEquals("[T][ ] Wash the dishes",task.taskslist.get(task.taskslist.size()-1).toString());
    }


}
