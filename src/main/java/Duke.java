import java.util.Scanner;


public class Duke {

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
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("So... how can I assist you?");

        String line = " ";
        Scanner in = new Scanner(System.in);

        Task[] storage = new Task[100];
        int inputCount = 0;
        while(!line.equals("bye")) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you soon!");
            }
            else if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < inputCount; i++) {
                    System.out.println(i + 1 + ". " + storage[i].getStatusIcon() + " " + storage[i].description);
                }
            }
            else if (line.startsWith("mark ")) {
                int index = Integer.parseInt(line.substring(5)) - 1; // e.g. 2. ITEM is index 1 in reality
                storage[index].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(" " + storage[index].getStatusIcon() + " " + storage[index].description);
            }
            else if (line.startsWith("unmark ")) {
                int index = Integer.parseInt(line.substring(7)) - 1;
                storage[index].unmarkAsDone();
                System.out.println("Nice! I've marked this task as not done yet: ");
                System.out.println(" " + storage[index].getStatusIcon() + " " + storage[index].description);
            }
            else {
                Task task = new Task(line);
                storage[inputCount] = task;
                inputCount++;
                System.out.println("added: " + task.description);
            }
        }
    }
}

