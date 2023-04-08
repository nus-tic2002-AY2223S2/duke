package formatDateTime;

import command.UnmarkCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LocalDateTimeTest
 * -> To test those methods in LocalDateTime
 */

public class LocalDateTimeTest {

    @Test
    public void LocalDateTimeStringTestOne(){
        String datetime = "31/12/2019 1000";
        LocalDateTime f = new LocalDateTime();
        assertEquals("31st of December 2019, 10AM", f.formatDate(datetime));
    }

    @Test
    public void LocalDateTimeStringTestTwo(){
        String datetime = "22/12/2019 1800";
        LocalDateTime f = new LocalDateTime();
        assertEquals("22nd of December 2019, 6PM", f.formatDate(datetime));
    }

    @Test
    public void LocalDateTimeStringTestThree(){
        String datetime = "3/11/2010 1000";
        LocalDateTime f = new LocalDateTime();
        assertEquals("3rd of November 2010, 10AM", f.formatDate(datetime));
    }

    @Test
    public void LocalDateTimeStringTestFour(){
        String datetime = "30/04/2020 0800";
        LocalDateTime f = new LocalDateTime();
        assertEquals("30th of April 2020, 8AM", f.formatDate(datetime));
    }
}
