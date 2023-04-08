package task;

import task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TaskTest
 * -> To test those methods in task.Task
 */

public class TaskTest {

    @Test
    public void TaskDesciptionTest() {
        String TaskDesciption = "Borrow Java coding book";
        Task t = new Task(TaskDesciption);
        assertEquals(TaskDesciption, t.description);
    }

    @Test
    public void TaskStatusTest() {
        String TaskDesciption = "Borrow Java coding book";
        Task t = new Task(TaskDesciption);
        assertEquals(false, t.isDone);
    }

    @Test
    public void TaskMarkTest() {
        String TaskDesciption = "Borrow Java coding book";
        Task t = new Task(TaskDesciption);
        t.mark();
        assertEquals(true, t.isDone);
        assertEquals("[X]", t.getStatusIcon());
        assertEquals("| 1 |", t.saveStatusIcon());
    }

    @Test
    public void TaskNotMarkTest() {
        String TaskDesciption = "Borrow Java coding book";
        Task t = new Task(TaskDesciption);
        t.mark();
        t.notMark();
        assertEquals(false, t.isDone);
        assertEquals("[ ]", t.getStatusIcon());
        assertEquals("| 0 |", t.saveStatusIcon());
    }

    @Test
    public void TasktoStringTest() {
        String TaskDesciption = "Borrow Java coding book";
        Task t = new Task(TaskDesciption);
        assertEquals("[ ] Borrow Java coding book", t.toString());
    }

    @Test
    public void TasktoSaveTest() {
        String TaskDesciption = "Borrow Java coding book";
        Task t = new Task(TaskDesciption);
        t.mark();
        assertEquals("| 1 | Borrow Java coding book", t.toSave());

    }
}
