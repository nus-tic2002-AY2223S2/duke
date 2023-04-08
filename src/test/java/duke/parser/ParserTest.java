package duke.parser;

import duke.command.Command;
import duke.task.Task;
import duke.type.CommandType;
import duke.type.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseTest() {
        Command command1 = Parser.parse("list");
        assertEquals("list", command1.getCommand());
        assertEquals(CommandType.LIST, command1.getCommandType());
        assertFalse(command1.isExit());

        Command command2 = Parser.parse("bye");
        assertEquals("bye", command2.getCommand());
        assertEquals(CommandType.EXIT, command2.getCommandType());
        assertTrue(command2.isExit());
    }

    @Test
    public void parseCommandTypeTest() {
        String command1 = "list";
        CommandType commandType1 = Parser.parseCommandType(command1);
        assertEquals(CommandType.LIST, commandType1);

        String command2 = "bye";
        CommandType commandType2 = Parser.parseCommandType(command2);
        assertEquals(CommandType.EXIT, commandType2);
    }

    @Test
    public void getTaskTest() {
        String command1 = "todo eat breakfast";
        Task todo = Parser.getTask(command1);
        assertEquals(TaskType.TODO, todo.getType());

        String command2 = "deadline SE PROJECT /by 01/04/2023";
        Task deadline = Parser.getTask(command2);
        assertEquals(TaskType.DEADLINE, deadline.getType());

        String command3 = "event SE Course /from 01/01/2023 /to 16/04/2023";
        Task event = Parser.getTask(command3);
        assertEquals(TaskType.EVENT, event.getType());
    }
}
