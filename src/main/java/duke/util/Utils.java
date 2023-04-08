package duke.util;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static <T> ArrayList<T> listToArrayList(List<T> list) {
        return list != null ? new ArrayList<>(list) : null;
    }

}
