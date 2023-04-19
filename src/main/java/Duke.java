import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }


    public static class Task {
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

        @Override
        public String toString() {
            return getStatusIcon() + " " + description;
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T] " + super.toString();
        }
    }

    public static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D] " + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E] " + super.toString() + " (from: " + from + "| to: " + to + ")";
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Please add your todo / deadline / event! (-:");

        String line = " ";
        Scanner in = new Scanner(System.in);

        ArrayList<Task> storage = new ArrayList<>();
        while(!line.equals("bye")) {
            line = in.nextLine();
            try {
                if (line.equals("bye")) {
                    System.out.println("Bye. Hope to see you soon!");
                } else if (line.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storage.size(); i++) {
                        System.out.println(i + 1 + ". " + storage.get(i).toString());
                    }
                } else if (line.startsWith("mark ")) {
                    int index = Integer.parseInt(line.substring(5)) - 1; // e.g. 2. ITEM is index 1 in reality
                    storage.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(" " + storage.get(index).getStatusIcon() + " " + storage.get(index).description);
                } else if (line.startsWith("unmark ")) {
                    int index = Integer.parseInt(line.substring(7)) - 1;
                    storage.get(index).unmarkAsDone();
                    System.out.println("Nice! I've marked this task as not done yet: ");
                    System.out.println(" " + storage.get(index).getStatusIcon() + " " + storage.get(index).description);
                } else if (line.startsWith("todo ")) {
                    if (line.length() <= 5) {
                        throw new DukeException("YO! The description of a todo cannot be empty!");
                    }
                    ToDo task = new ToDo(line);
                    storage.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + task.toString());
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } else if (line.startsWith("deadline ")) {
                    int separatorIndex = line.indexOf("/by ");
                    if (separatorIndex == -1) {
                        throw new DukeException("YO! The deadline must have a '/by' followed by a date!");
                    }
                    String description = line.substring(9, separatorIndex - 1);
                    if (description.isEmpty()) {
                        throw new DukeException("YO! There must be a description for your deadline!");
                    }
                    String by = line.substring(separatorIndex + 4);
                    Deadline task = new Deadline(description, by);
                    storage.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + task.toString());
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } else if (line.startsWith("event ")) {
                    int fromIndex = line.indexOf("/from ");
                    int toIndex = line.indexOf("/to ");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new DukeException("YO! The event must have '/from' and '/to' followed by dates.");
                    }
                    String description = line.substring(6, fromIndex - 1);
                    if (description.isEmpty()) {
                        throw new DukeException("YO! There must be a description for your event!");
                    }
                    String from = line.substring(fromIndex + 6, toIndex);
                    String to = line.substring(toIndex + 4);
                    Event task = new Event(description, from, to);
                    storage.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + task.toString());
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } else if (line.startsWith("delete ")) {
                    int index = Integer.parseInt(line.substring(7)) - 1;
                    if (index < 0 || index >= storage.size()) {
                        throw new IndexOutOfBoundsException();
                    }
                    Task removedTask = storage.remove(index);
                    System.out.println("Noted. I have removed this task:");
                    System.out.println(" " + removedTask.toString());
                    System.out.println("Now you have " + storage.size() + " tasks in the list.");
                } else {
                    throw new DukeException("I have no idea what you just typed lmao");
                }
            } catch (DukeException e) {
                System.out.println("------------------------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println("YO! The task number must be a valid integer!");
                System.out.println("____________________________________________________________");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("____________________________________________________________");
                System.out.println("YO! The task number is out of range!");
                System.out.println("____________________________________________________________");
            } catch (NullPointerException e) {
                System.out.println("____________________________________________________________");
                System.out.println("YO! The task number is out of range!");
                System.out.println("____________________________________________________________");
            }
        }
    }
}

