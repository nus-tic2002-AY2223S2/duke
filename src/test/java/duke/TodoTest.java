package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void createTodoTest(){
        Task newTask = new Todo("Buy food");

        assertEquals("Buy food", newTask.getDescription());
    }
}
