package parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import command.*;

/**
 * ParserTest
 * -> To test those methods in Parser.java
 */
public class ParserTest{

    @Test
    public void ListCommandTest() {
        Commands c = Parser.parse("list");
        assertSame(listArray.class, c.getClass());
    }

    @Test
    public void ByeCommandTest() {
        Commands c = Parser.parse("bye");
        assertSame(ByeCommand.class, c.getClass());
    }

    @Test
    public void MarkCommandTest() {
        Commands c = Parser.parse("mark 1");
        assertSame(MarkCommand.class, c.getClass());
    }

    @Test
    public void UnmarkCommandTest() {
        Commands c = Parser.parse("unmark 1");
        assertSame(UnmarkCommand.class, c.getClass());
    }

    @Test
    public void DeadlineCommandTest() {
        Commands c = Parser.parse("deadline return book /by 2/12/2019 1800");
        assertSame(DeadlineCommand.class, c.getClass());
    }

    @Test
    public void TodoCommandTest() {
        Commands c = Parser.parse("todo read book");
        assertSame(TodoCommand.class, c.getClass());
    }

    @Test
    public void EventCommandTest() {
        Commands c = Parser.parse("event project meeting /from 2/12/2019 1200 /to 2/12/2019 1800");
        assertSame(EventCommand.class, c.getClass());
    }

    @Test
    public void DeleteCommandTest() {
        Commands c = Parser.parse("delete 1");
        assertSame(DeleteCommand.class, c.getClass());
    }
}
