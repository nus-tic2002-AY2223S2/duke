package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * ListCommandTest
 * -> To test those methods in ListArray
 */
public class ListCommandTest {

    @Test
    public void ListExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        //tasks
        task.taskslist.add(new ToDo("Wash the dishes"));
        listArray l = new listArray();
        l.execute(task, ui, storage);
        assertEquals("[[T][ ] Wash the dishes]", task.getTasks().toString());
    }
}
