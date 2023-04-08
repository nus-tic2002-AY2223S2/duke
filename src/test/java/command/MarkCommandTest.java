package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * MarkCommandTest
 * -> To test those methods in MarkCommand
 */

public class MarkCommandTest {

    @Test
    public void MarkCommandStringTest(){
        MarkCommand m = new MarkCommand(1);
        assertEquals(1, m.posNo);
    }

    @Test
    public void MarkExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        //tasks
        task.taskslist.add(new ToDo("Wash the dishes"));
        task.taskslist.add(new ToDo("Prepare the dishes"));
        MarkCommand m = new MarkCommand(1);
        m.execute(task, ui, storage);
        assertEquals("[X]", task.taskslist.get(1).getStatusIcon());
    }
}
