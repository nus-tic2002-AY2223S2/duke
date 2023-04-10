package duke.Exception;

public class DukeException extends Exception
{
    /**
     * This method is used for specifying exception outputs
     * @param errorQuestion takes in a string of error message defined in the Parser class validateQuestion() method
     */
    public DukeException(String errorQuestion) {
        super(errorQuestion);
    }
}
