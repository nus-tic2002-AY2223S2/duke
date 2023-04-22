package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TaskTest {

    @Test
    public void testGetDescription() {
        Task task = new Task("Test task");
        assertEquals("Test task", task.getDescription());
    }
}
