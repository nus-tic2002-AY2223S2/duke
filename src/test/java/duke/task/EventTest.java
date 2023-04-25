package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {
    @Test
    public void testStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse("2023-03-17 17:00", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2023-03-17 18:30", formatter);
        assertEquals("\t [E][ ] project meeting (From: Mar 17 2023 17:00 to Mar 17 2023 18:30)", new Event("project meeting", startTime, endTime).toString());
    }
}
