package duke;

import duke.TaskList.TaskList;
import duke.TasksType.Task;
import org.junit.jupiter.api.Test;
import duke.TasksType.Todo;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void changePriorityTest(){

        ArrayList<Task> list = new ArrayList<>();
        Task task = new Todo("Eat dinner");
        list.add(task);
        TaskList taskList = new TaskList();
        taskList.storeList(list);
        taskList.setPriority(0, Task.priorityLevel.High);

        assertEquals(Task.priorityLevel.High, taskList.getTask(0).getPriorityLevel());
    }
}
