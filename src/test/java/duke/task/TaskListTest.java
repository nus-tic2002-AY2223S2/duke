package duke.task;

import duke.DukeException;
import duke.Task;
import duke.Todo;
import duke.command.Command;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    /**
     * validateDeleteTaskTest will validate both add and delete task by checking the size of the lists
     */
    @Test
    public void validateAddThenDeleteTaskTest(){
        TaskList taskListObj = new TaskList();
        Task newTask = new Todo("Breakfast");

        try {
            taskListObj.addNewTask(newTask);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, taskListObj.getSizeOfList());

        try {
            int index = 0;
            taskListObj.deleteTask(index);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

        assertEquals(0, taskListObj.getSizeOfList());

    }

}
