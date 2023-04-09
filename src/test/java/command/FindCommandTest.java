package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * FindCommandTest
 * -> To test those methods in FindCommand
 */

public class FindCommandTest {

    @Test
    public void FindCommandStringTest(){
        FindCommand f = new FindCommand("save");
        assertEquals("save", f.keyword);
    }

}
