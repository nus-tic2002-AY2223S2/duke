import executecommand.Command;
import executecommand.ListCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.Storage;
import task.Task;

class ListCommandTest {

    @Test
    void execute() {
        Task task1 = new Task("todo borrow book");
        Task task2 = new Task("deadline return book /by Sunday");
        Task task3 = new Task("done 2");
        Storage.getStorage().taskAdd(task1);
        Storage.getStorage().taskAdd(task2);
        Storage.getStorage().taskAdd(task3);

        Command c = new ListCommand("list");
        c.execute();
        Task actual1 = Storage.getStorage().tasksList.get(0);
        Task actual2 = Storage.getStorage().tasksList.get(1);
        Task actual3 = Storage.getStorage().tasksList.get(2);

        Assertions.assertEquals(task1,actual1);
        Assertions.assertEquals(task2,actual2);
        Assertions.assertEquals(task3,actual3);
    }
}