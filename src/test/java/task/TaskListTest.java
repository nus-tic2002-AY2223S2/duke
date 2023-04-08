package task;

import task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TaskListTest
 * -> To test those methods in task.TaskList
 */

public class TaskListTest {

    @Test
    public void TaskListSizeTest() {
        TaskList tL = new TaskList();
        assertEquals(0, tL.taskslist.size());
    }


}
