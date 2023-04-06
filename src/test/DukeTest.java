import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void run() {
        Parser parser = new Parser();
        String userInput = "deadline return book /by 2/12/2019 1800";
        Task t;
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            //Exit the chatbot.
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (parser.isListCommand(userInput)) {
                //List out all the elements stored.
                tasks.listTasks();
            }   else if (parser.isToDoCommand(userInput)) {
                //Create to-do task
                t = parser.createToDo(userInput);
                tasks.addTask(t);
            } else if (parser.isDeadlineCommand(userInput)) {
                //Create duke.task.Deadline task
                t = parser.createDeadline(userInput);
                tasks.addTask(t);
            } else if (parser.isEventCommand(userInput)) {
                //Create event task
                t = parser.createEvent(userInput);
                tasks.addTask(t);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ui.showLine();
        }
        assertEquals("[D][ ][ ] return book(by: 02 Dec 2019 6PM)", tasks.getTask(0).toString());
    }
}
