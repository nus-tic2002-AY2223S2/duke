package task;

import task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DeadlineTest
 * -> To test those methods in task.Deadline
 */

public class DeadlineTest {

    @Test
    public void DeadlineDesciptionTest() {
        String DeadlineDesciption = "Submit assignment";
        String DeadlineBy = "08th of April 2023, 5PM";
        Deadline d = new Deadline(DeadlineDesciption, DeadlineBy);
        assertEquals(DeadlineDesciption, d.description);
    }

    @Test
    public void DeadlineByTest() {
        String DeadlineDesciption = "Submit assignment";
        String DeadlineBy = "08th of April 2023, 5PM";
        Deadline d = new Deadline(DeadlineDesciption, DeadlineBy);
        assertEquals(DeadlineBy, d.by);
    }

    @Test
    public void DeadlinetoStringTest() {
        String DeadlineDesciption = "Submit assignment";
        String DeadlineBy = "08th of April 2023, 5PM";
        Deadline d = new Deadline(DeadlineDesciption, DeadlineBy);
        assertEquals("[D][ ] Submit assignment (by: 08th of April 2023, 5PM)", d.toString());
    }

    @Test
    public void DeadlinetoSaveTest() {
        String DeadlineDesciption = "Submit assignment";
        String DeadlineBy = "08th of April 2023, 5PM";
        Deadline d = new Deadline(DeadlineDesciption, DeadlineBy);
        assertEquals("D | 0 | Submit assignment | 08th of April 2023, 5PM", d.toSave());
    }
}
