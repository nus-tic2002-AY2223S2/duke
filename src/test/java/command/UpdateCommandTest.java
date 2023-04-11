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
 * UpdateCommandTest
 * -> To test those methods in UpdateCommand
 */
public class UpdateCommandTest {

    @Test
    public void UpdateCommandStringTest() {
        int row = 1;
        String replaceTask = "edit";
        String updateTask = "update";
        UpdateCommand u = new UpdateCommand(row, replaceTask, updateTask);
        assertEquals(1, u.updateNum);
        assertEquals(replaceTask, u.replacedTask);
        assertEquals(updateTask, u.updateTask);
    }

    @Test
    public void UpdateExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        task.taskslist.add(new ToDo("Wash the dishes"));
        task.taskslist.add(new ToDo("Prepare the dishes"));
        int row = 1;
        String replaceTask = "dishes";
        String updateTask = "clothes";
        UpdateCommand u = new UpdateCommand(row, replaceTask, updateTask);
        u.execute(task, ui, storage);
        assertEquals("[T][ ] Prepare the clothes",task.taskslist.get(1).toString());
    }
}
