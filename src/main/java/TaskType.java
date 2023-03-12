public enum TaskType {
    TODO("TODO"),
    DEADLINE("DEADLINE"),
    EVENT("EVENT"),
    UNKNOWN("UNKNOWN");
    private final String name;

    TaskType(String s) {
        name = s;
    }

}
