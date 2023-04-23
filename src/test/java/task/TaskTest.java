package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void markAsDone_marksTaskAsDone_success() {
        Task task = new Task("Sell soul") {
        };
        task.markAsDone();
        String expectedOutput = "[X] Sell soul";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    void toString_returnsCorrectFormat_success() {
        Task task = new Task("Buy koi milktea") {
        };
        String expectedOutput = "[ ] Buy koi milktea";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    void toFileFormat_returnsCorrectFormat_success() {
        Task task = new Task("Pet corgi") {
        };
        String expectedOutput = "0 | Pet corgi";
        assertEquals(expectedOutput, task.toFileFormat());
    }
}
