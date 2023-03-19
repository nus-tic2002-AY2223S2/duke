package ExecuteCommand;

import Task.Todo;
import Exception.DukeException;
import Storage.*;
import Parser.*;

public class TODO extends Command{
    private final String inputCommand;
    public static final Storage storage = Storage.getStorage();

    public TODO(String inputCommand) {
        this.inputCommand = inputCommand;
    }
    public void execute()throws DukeException {
        if(Parser.commandLength(inputCommand) < 2){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(Parser.restCommand(inputCommand));
        storage.taskAdd(todo);
    }
}
