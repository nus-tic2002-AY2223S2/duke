package duke;

public class Util {
    public static boolean checkMark(String input) {
        String[] splitted = input.split(" ");
        return splitted.length == 2;
    }
}
