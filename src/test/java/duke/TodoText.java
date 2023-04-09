package duke;


import duke.TasksType.Task;
import org.junit.jupiter.api.Test;
import duke.TasksType.Todo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoText {
    @Test
    public void validateTodoDescription(){
        Task task = new Todo("Eat lunch");
        assertEquals("Eat lunch", task.getDescription());
    }
}
