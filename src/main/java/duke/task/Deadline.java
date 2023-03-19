package duke.task;

import duke.type.TaskType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toCommand() {
        return getType().name() + " " + description + " /by " + by;
    }

    public TaskType getType(){
        return TaskType.DEADLINE;
    }
}

