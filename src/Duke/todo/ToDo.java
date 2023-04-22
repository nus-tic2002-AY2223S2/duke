package Duke.todo;

import Duke.task.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        int markAsDone = super.isDone() ? 1 : 0;
        char isPriority = super.isPriority() ? 'P' : 'N';
        return "T|" + markAsDone + "|" + super.getDescription() + "|" + isPriority;
    }
}