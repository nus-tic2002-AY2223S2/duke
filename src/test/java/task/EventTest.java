package task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    void parseDateTime_validInput_success() {
        LocalDateTime expectedDateTime = LocalDateTime.of(2021, 12, 2, 18, 0);
        LocalDateTime actualDateTime = Event.parseDateTime("02/12/2021 1800");
        assertEquals(expectedDateTime, actualDateTime);
    }

    @Test
    void formatDateTime_validInput_success() {
        String expectedFormattedDateTime = "Dec 02 2021, 06:00 pm";
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 2, 18, 0);
        String actualFormattedDateTime = Event.formatDateTime(dateTime);
        assertEquals(expectedFormattedDateTime, actualFormattedDateTime);
    }

    @Test
    void eventToString_returnsCorrectFormat_success() {
        String start;
        String end;
        start = "02/07/2020 1800";
        end = "02/07/2020 2000";
        Event event = new Event("Project meeting", Event.parseDateTime(start), Event.parseDateTime(end));
        String expectedOutput = "[E] [ ] Project meeting (from: Jul 02 2020, 06:00 pm | to: Jul 02 2020, 08:00 pm)";
        assertEquals(expectedOutput, event.toString());
    }
}
