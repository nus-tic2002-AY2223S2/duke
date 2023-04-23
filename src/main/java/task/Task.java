package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an abstract task with a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 0;// default priority = 0 (lowest)
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");

        // Check if the input line has the correct format
        if (parts.length < 3 || parts.length > 5) {
            // System.err.println("Invalid line format: " + line);
            return null;
        }

        Task task = null;
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                if (parts.length != 4) {
                    System.err.println("Invalid line format for deadline " + line);
                    return null;
                }
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length != 5) {
                    System.err.println("Invalid line format for event: " + line);
                    return null;
                }
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                task = new Event(description, from, to);
                break;
            default:
                System.err.println("Invalid task type: " + taskType);
                return null;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description + (priority > 0 ? " (P" + priority + ")" : "");
    }
}