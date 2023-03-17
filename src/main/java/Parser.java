public class Parser {
    public static boolean isToDoCommand(String command) {
        String[] wordsInSentences = command.split(" ");
        if (String.valueOf(wordsInSentences[0].toUpperCase()) == "todo") {
            return true;
        }
        else return false;
    }

    public static Object createTodo(String command) {
    }
}
