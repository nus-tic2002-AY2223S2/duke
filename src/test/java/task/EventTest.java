package task;

import task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EventTest
 * -> To test those methods in task.Event
 */

public class EventTest {

    @Test
    public void EventDesciptionTest() {
        String EventDesciption = "Attend java coding workshop";
        String EventFrom = "05th of April 2023, 2PM";
        String EventTo = "05th of April 2023, 5PM";
        Event e = new Event(EventDesciption, EventFrom, EventTo);
        assertEquals(EventDesciption, e.description);
    }

    @Test
    public void EventFromTest() {
        String EventDesciption = "Attend java coding workshop";
        String EventFrom = "05th of April 2023, 2PM";
        String EventTo = "05th of April 2023, 5PM";
        Event e = new Event(EventDesciption, EventFrom, EventTo);
        assertEquals(EventFrom, e.from);
    }

    @Test
    public void EventToTest() {
        String EventDesciption = "Attend java coding workshop";
        String EventFrom = "05th of April 2023, 2PM";
        String EventTo = "05th of April 2023, 5PM";
        Event e = new Event(EventDesciption, EventFrom, EventTo);
        assertEquals(EventTo, e.to);
    }

    @Test
    public void EventtoStringTest() {
        String EventDesciption = "Attend java coding workshop";
        String EventFrom = "05th of April 2023, 2PM";
        String EventTo = "05th of April 2023, 5PM";
        Event e = new Event(EventDesciption, EventFrom, EventTo);
        assertEquals("[E][ ] Attend java coding workshop (from: 05th of April 2023, 2PM to: 05th of April 2023, 5PM)", e.toString());
    }

    @Test
    public void EventtoSaveTest() {
        String EventDesciption = "Attend java coding workshop";
        String EventFrom = "05th of April 2023, 2PM";
        String EventTo = "05th of April 2023, 5PM";
        Event e = new Event(EventDesciption, EventFrom, EventTo);
        assertEquals("E | 0 | Attend java coding workshop | 05th of April 2023, 2PM-05th of April 2023, 5PM", e.toSave());
    }
}
