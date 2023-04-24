package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse("2019-02-12 18:00", formatter);
        assertEquals("\t [D][ ] return book (by: Feb 12 2019 18:00)", new Deadline("return book", time).toString());
    }

}
