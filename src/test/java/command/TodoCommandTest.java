package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.time.format.DateTimeFormatter;

/**
 * EventCommandTest
 * -> To test those methods in EventCommand
 */

public class TodoCommandTest {

    @Test
    public void DeadlineCommandStringTest() {
        String todoDescription = "Study for exam";
        TodoCommand t = new TodoCommand(todoDescription);
        assertEquals(todoDescription, t.description);
    }

    @Test
    public void EventExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        //deadline task
        String todoDescription = "Attend a meeting";
        TodoCommand t = new TodoCommand(todoDescription);
        t.execute(task, ui, storage);
        assertEquals("[T][ ] Attend a meeting", task.taskslist.get(0).toString());
    }

}
