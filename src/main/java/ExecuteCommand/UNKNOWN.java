package ExecuteCommand;
import Exception.DukeException;

public class UNKNOWN extends Command{
    /**
     * if user key in the wrong keyword, duke will not recognize.
     * @throws DukeException
     */
    public void execute() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
