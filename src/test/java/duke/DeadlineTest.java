package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;



public class DeadlineTest {


    @Test
    void setByTest() {
        Deadline deadline = new Deadline("Submit application", "2023-07-20");
        deadline.setBy("2023-07-20");
        Assertions.assertEquals("2023-07-20", deadline.getBy());
    }
}
