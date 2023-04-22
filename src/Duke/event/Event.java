package Duke.event;

import Duke.task.Task;

public class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

    @Override
    public String toFileString() {
        int markAsDone = super.isDone() ? 1 : 0;
        char isPriority = super.isPriority() ? 'P' : 'N';
        return "E|" + markAsDone + "|" + super.getDescription() + "|" + by + "|" + isPriority;
    }
}