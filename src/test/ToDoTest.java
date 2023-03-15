import org.junit.Test;

import static org.junit.Assert.*;

public class ToDoTest {

    @Test
    public void getTaskType() {
        String TaskType ="T";
        Task Task = new ToDo("Bring Phone", TaskType);

        assertEquals("Bring Phone",Task.getDescription());

    }
}