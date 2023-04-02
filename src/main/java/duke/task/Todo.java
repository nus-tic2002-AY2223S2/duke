package duke.task;

import duke.type.TaskType;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toCommand() {
        return getType().name() + " " + description;
    }

    public TaskType getType() {
        return TaskType.TODO;
    }
}
