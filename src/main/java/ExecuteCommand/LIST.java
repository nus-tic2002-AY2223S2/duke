package ExecuteCommand;
import Storage.*;

public class LIST extends Command{
    public static final Storage storage = Storage.getStorage();

    public void execute() {
        storage.showToUser();
    }
}
