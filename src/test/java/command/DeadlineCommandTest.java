package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DeadlineCommandTest
 * -> To test those methods in DeadlineCommand
 */
public class DeadlineCommandTest{

    @Test
    public void DeadlineCommandStringTest() {
        String deadlineDescription = "Return book";
        String deadlineDueDate = "2/12/2019 1800";
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        java.time.LocalDateTime date = java.time.LocalDateTime.parse(deadlineDueDate, formatting);
        String updatedDateTime = date.format(DateTimeFormatter.ofPattern("d'nd' 'of' MMMM yyyy, ha"));
        DeadlineCommand d = new DeadlineCommand(deadlineDescription, updatedDateTime);
        assertEquals(deadlineDescription,d.description);
        assertEquals("2nd of December 2019, 6PM",d.by);
    }

    @Test
    public void DeadlineExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        //deadline task
        String deadlineDescription = "Submit homework";
        String DeadlineDueDate = "2/12/2019 1800";
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        java.time.LocalDateTime date = java.time.LocalDateTime.parse(DeadlineDueDate, formatting);
        String updatedDateTime = date.format(DateTimeFormatter.ofPattern("d'nd' 'of' MMMM yyyy, ha"));
        DeadlineCommand d = new DeadlineCommand(deadlineDescription, updatedDateTime);
        d.execute(task, ui, storage);
        assertEquals("[D][ ] Submit homework (by: 2nd of December 2019, 6PM)", task.taskslist.get(0).toString());
    }
}
