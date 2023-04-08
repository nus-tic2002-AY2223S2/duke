package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void getTaskListTest() {
        TaskList tasklist = new TaskList();
        assertEquals("", tasklist.getTaskListString());
    }

    @Test
    public void getSizeTest() {
        TaskList tasklist = new TaskList();
        assertEquals(0, tasklist.getSize());
    }

    @Test
    public void addItemTest() {
        Task task = new Todo("Test Item");
        TaskList tasklist = new TaskList();
        tasklist.addItem(task);
        assertEquals(1, tasklist.getSize());
    }

    @Test
    public void removeItemTest() {
        Task task = new Todo("Test Item");
        TaskList tasklist = new TaskList();
        tasklist.addItem(task);
        tasklist.removeItem(0);
        assertEquals(0, tasklist.getSize());
    }


    @Test
    public void getItemTest() {
        Task task = new Todo("Test Item");
        TaskList tasklist = new TaskList();
        tasklist.addItem(task);
        assertEquals("Test Item", tasklist.getItem(0).description);
    }
}
