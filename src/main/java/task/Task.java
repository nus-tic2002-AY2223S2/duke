package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length != 5) {
                    System.err.println("Invalid line format for event: " + line);
                    return null;
                }
                String from = parts[3];
                String to = parts[4];
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
        return getStatusIcon() + " " + description;
    }
}