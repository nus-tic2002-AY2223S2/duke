package duke;

import duke.Parser.Parser;
import duke.TasksType.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void unknownCommandGiven() {
        ArrayList<Task> list = new ArrayList<>();
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.validateQuestion("eventt", list);
        });

        String expectedOutput = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualOutput = exception.getMessage();

        assertTrue(actualOutput.contains(expectedOutput));
    }
}
