package duke.parser;

import duke.DukeException;
import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {

    /**
     * Exception test reference from the following site:
     * Reference: https://www.baeldung.com/junit-assert-exception#:~:text=When%20using%20JUnit%204%2C%20we,in%20the%20annotated%20test%20method.&text=In%20this%20example%2C%20we've,to%20result%20in%20a%20NullPointerException.
     * validateUnknownCommand() method tests exception should throw if we provide an unknown command
     */
    @Test
    public void validateUnknownCommand_ShouldThrowExceptionTest(){
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("do nothing");
        });

        String anticipatedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String realMessage = exception.getMessage();

        assertEquals(anticipatedMessage, realMessage);
    }

    /**
     * validateByeCommand will test if bye command is successfully parsed through and command class will be created with a isByeAttribute true
     */
    @Test
    public void validateByeCommandTest(){
        Command objC = null;
        try {
            objC = Parser.parse("bye");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
        assertTrue(objC.isItBye());
    }


}
