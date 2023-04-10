package duke;

import duke.TaskList.TaskList;
import duke.TasksType.Task;
import duke.TasksType.Todo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    /**
     * The Junit tests the addTask method in the Command class
     * adds two tasks and asserts that the description is stored and retrieved correctly
     */
    @Test
    public void addTask() {
        ArrayList<Task> list = new ArrayList<>();
        Task task = new Todo("Eat dinner");
        list.add(task);
        Task task2 = new Todo("Eat breakfast");
        list.add(task2);
        TaskList taskList = new TaskList();
        taskList.storeList(list);

        assertEquals("Eat dinner", taskList.getTask(0).getDescription());
        assertEquals("Eat breakfast", taskList.getTask(1).getDescription());
    }
}

