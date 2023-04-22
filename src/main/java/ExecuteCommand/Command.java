package executecommand;

import storage.Storage;

public abstract class Command{
    protected String inputCommand;
    public static final Storage storage = Storage.getStorage();

    protected Command(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    public abstract void execute();
}