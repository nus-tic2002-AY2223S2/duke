package command;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.ToDo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {
    @Test
    void createTask_validToDoInput_success() throws Exception {
        AddCommand addCommand = new AddCommand("todo Buy groceries");
        assertTrue(addCommand.createTask("todo Buy groceries") instanceof ToDo);
    }

    @Test
    void createTask_validDeadlineInput_success() throws Exception {
        AddCommand addCommand = new AddCommand("deadline Return book /by 02/12/2019 1800");
        assertTrue(addCommand.createTask("deadline Return book /by 02/12/2019 1800") instanceof Deadline);
    }

    @Test
    void createTask_validEventInput_success() throws Exception {
        AddCommand addCommand = new AddCommand("event Meeting /from 02/12/2019 0900 /to 02-12-2019 1100");
        assertTrue(addCommand.createTask("event Meeting /from 02/12/2019 0900 /to 02/12/2019 1100") instanceof Event);
    }
}
