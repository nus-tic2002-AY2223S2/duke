package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * DeleteCommandTest
 * -> To test those methods in DeleteCommand
 */

public class DeleteCommandTest {

    @Test
    public void DeleteCommandStringTest() {
        DeleteCommand del = new DeleteCommand(1);
        assertEquals(1, del.posNo);
    }

    @Test
    public void DeleteExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        //tasks
        task.taskslist.add(new ToDo("Wash the dishes"));
        task.taskslist.add(new ToDo("Prepare the dishes"));
        DeleteCommand del = new DeleteCommand(0);
        del.execute(task, ui, storage);
        assertEquals("[T][ ] Prepare the dishes",task.taskslist.get(0).toString());
    }
}
