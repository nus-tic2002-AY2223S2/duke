package tasklist;

import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    void addTask_addsTaskToList_success() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("buy nintendo switch");
        taskList.add(task);
        assertEquals(1, taskList.size());
    }

    @Test
    void removeTask_removesTaskFromList_success() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("buy nintendo switch");
        taskList.add(task);
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }
}
