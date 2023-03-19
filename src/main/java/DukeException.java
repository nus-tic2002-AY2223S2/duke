public class DukeException extends Throwable {

    private String message;
    public DukeException(String message) {
        this.message = message;
    }

    public static void checkDescriptionExist(int wordsDescription, int minWords) throws DukeException {
        if (wordsDescription < minWords) {
            throw new DukeException("no description");            // To handle exception: to-do
        }
    }

    public static void checkPartsRequirement(int partsDescription, int numberParts) throws IncompleteDescriptionException {
        if (partsDescription > numberParts) {
            throw new IncompleteDescriptionException("incomplete description");            // To handle exception: description more than 1 /by /from /to
        }
    }


    public static void checkTaskExist(int index, int min) throws NoTaskException {
        if (index == min) {
            throw new NoTaskException("no task");            // To handle exception: to-do
        }
    }

}
