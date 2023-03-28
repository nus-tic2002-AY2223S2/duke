package ExecuteCommand;
import Storage.*;

public class LIST extends Command{
    public static final Storage storage = Storage.getStorage();

    /**
     * all tasks to be printed.
     */
    public void execute() {
        storage.showToUser();
    }
}
