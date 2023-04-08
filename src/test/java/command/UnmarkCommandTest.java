package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * UnmarkCommandTest
 * -> To test those methods in UnmarkCommand
 */
public class UnmarkCommandTest {

    @Test
    public void UnmarkCommandStringTest(){
        UnmarkCommand u = new UnmarkCommand(1);
        assertEquals(1, u.posNo);
    }

    @Test
    public void UnmarkExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        //tasks
        task.taskslist.add(new ToDo("Wash the dishes"));
        task.taskslist.add(new ToDo("Prepare the dishes"));
        /*MarkCommand m = new MarkCommand(1);
        m.execute(task, ui, storage);
        assertEquals("X", task.taskslist.get(1).getStatusIcon());*/
        UnmarkCommand u = new UnmarkCommand(1);
        u.execute(task, ui, storage);
        assertEquals("[ ]", task.taskslist.get(1).getStatusIcon());
    }
}
