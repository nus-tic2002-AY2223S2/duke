package duke.command;

import duke.type.CommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void commandTest() {
        Command command = new Command(CommandType.LIST, "todo something");
        assertEquals(CommandType.LIST, command.getCommandType());
        assertEquals("todo something", command.getCommand());
        assertFalse(command.isExit());
    }

}
