package task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    void parseDateTime_validInput_success() {
        LocalDateTime expectedDateTime = LocalDateTime.of(2021, 12, 2, 18, 0);
        LocalDateTime actualDateTime = Deadline.parseDateTime("02/12/2021 1800");
        assertEquals(expectedDateTime, actualDateTime);
    }

    @Test
    void formatDateTime_validInput_success() {
        String expectedFormattedDateTime = "Dec 02 2021, 06:00 pm";
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 2, 18, 0);
        String actualFormattedDateTime = Deadline.formatDateTime(dateTime);
        assertEquals(expectedFormattedDateTime, actualFormattedDateTime);
    }

    @Test
    void deadlineToString_returnsCorrectFormat_success() {
        String by = "02/07/2020 1800";
        Deadline deadline = new Deadline("Project meeting", Deadline.parseDateTime(by));
        String expectedOutput = "[D] [ ] Project meeting (by: Jul 02 2020, 06:00 pm)";
        assertEquals(expectedOutput, deadline.toString());
    }
}
