package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void todoToString_returnsCorrectFormat_success() {
        ToDo todo = new ToDo("Go to the gym & film tiktok");
        String expectedOutput = "[T] [ ] Go to the gym & film tiktok";
        assertEquals(expectedOutput, todo.toString());
    }
}
