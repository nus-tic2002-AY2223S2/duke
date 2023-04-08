package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import java.time.format.DateTimeFormatter;

/**
 * EventCommandTest
 * -> To test those methods in EventCommand
 */

public class EventCommandTest {

    @Test
    public void EventCommandStringTest() {
        String eventDescription = "Training at MBS";
        String eventStartDate = "2/12/2019 1300";
        String eventEndate = "2/12/2019 1800";

        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        //format start date
        java.time.LocalDateTime startDate = java.time.LocalDateTime.parse(eventStartDate, formatting);
        String updatedStartDate = startDate.format(DateTimeFormatter.ofPattern("d'nd' 'of' MMMM yyyy, ha"));

        //format end date
        java.time.LocalDateTime endDate = java.time.LocalDateTime.parse(eventEndate, formatting);
        String updatedEndDate = endDate.format(DateTimeFormatter.ofPattern("d'nd' 'of' MMMM yyyy, ha"));

        EventCommand e = new EventCommand(eventDescription, updatedStartDate, updatedEndDate);
        assertEquals(eventDescription, e.description);
        assertEquals("2nd of December 2019, 1PM",e.from);
        assertEquals("2nd of December 2019, 6PM",e.to);
    }

    @Test
    public void EventExecuteString() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("duke-master\\data\\test.txt");

        //deadline task
        String eventDescription = "Training at school";
        String eventStartDate = "2/11/2020 0800";
        String eventEndate = "2/11/2020 1000";
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

        //format start date
        java.time.LocalDateTime startDate = java.time.LocalDateTime.parse(eventStartDate, formatting);
        String updatedStartDate = startDate.format(DateTimeFormatter.ofPattern("d'nd' 'of' MMMM yyyy, ha"));

        //format end date
        java.time.LocalDateTime endDate = java.time.LocalDateTime.parse(eventEndate, formatting);
        String updatedEndDate = endDate.format(DateTimeFormatter.ofPattern("d'nd' 'of' MMMM yyyy, ha"));
        EventCommand e = new EventCommand(eventDescription, updatedStartDate, updatedEndDate);
        e.execute(task, ui, storage);
        assertEquals("[E][ ] Training at school (from: 2nd of November 2020, 8AM to: 2nd of November 2020, 10AM)", task.taskslist.get(0).toString());
    }
}
