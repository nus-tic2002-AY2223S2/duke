package task;

import task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TodoTest
 * -> To test those methods in task.ToDo
 */


public class TodoTest {

    @Test
    public void ToDoDesciptionTest() {
        String ToDoDesciption = "Return a book";
        ToDo td = new ToDo(ToDoDesciption);
        assertEquals(ToDoDesciption, td.description);
    }

    @Test
    public void ToDotoStringTest() {
        String ToDoDesciption = "Return a book";
        ToDo td = new ToDo(ToDoDesciption);
        assertEquals("[T][ ] Return a book", td.toString());
    }

    public void ToDotoSaveTest() {
        String ToDoDesciption = "Return a book";
        ToDo td = new ToDo(ToDoDesciption);
        assertEquals("T | 0 | Return a book", td.toSave());
    }
}
