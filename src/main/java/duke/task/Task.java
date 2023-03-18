package duke.task;

import duke.type.TaskType;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unMarkDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public abstract String toCommand();

    public abstract TaskType getType();
}


