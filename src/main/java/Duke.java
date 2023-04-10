import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        // create an array of task
        ArrayList<Task> list_of_task = new ArrayList<Task>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) continue;
            String[] strArr = input.split(" ");

            if (!input.contains("list")
                && !input.contains("bye")
                && !input.contains("mark")
                && !input.contains("unmark")
                && !input.contains("todo")
                && !input.contains("deadline")
            ) {
                Task new_task = new Task(strArr[0]);
                list_of_task.add(new_task);
                System.out.println("added:" + new_task.description);
            }

            if (input.matches("deadline(.*)")) {
                // find by/
                int stop = 0;
                String found = "/by";

                for(int i = 0; i < input.length(); i++)
                {
                    if(found.equals(strArr[i]))
                    {
                        stop = i;
                        break;
                    }
                }
                String[] taskArray = Arrays.copyOfRange(strArr, 1, stop);
                String[] timingArray = Arrays.copyOfRange(strArr, stop+1, strArr.length);

                String task = String.join(" ", taskArray);
                String timing = String.join(" ", timingArray);

                Deadline deadline = new Deadline(task, timing);
                list_of_task.add(deadline);
                System.out.println("Got it. I've added this task:\n" + deadline.toString());
            }

            if (input.matches("todo(.*)")) {
                String[] modifiedArray = Arrays.copyOfRange(strArr, 1, strArr.length);
                String joinedString = String.join(" ", modifiedArray);

                Todo to_do_task = new Todo(joinedString);
                list_of_task.add(to_do_task);
                System.out.println("Got it. I've added this task:\n" + to_do_task.toString());
                System.out.println(String.format("Now you have %s tasks in the list.", list_of_task.size()));

            }

            if (input.matches("mark(.*)")) {
                String num_input = input.replaceAll("\\D+","");
                int i = Integer.parseInt(num_input);

                if (i > list_of_task.size()) continue;
                list_of_task.get(i - 1).markDone();

                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list_of_task.get(i - 1).getDescription());
            }

            if (input.matches("unmark(.*)")) {
                String num_input = input.replaceAll("\\D+","");
                int i = Integer.parseInt(num_input);

                if (i > list_of_task.size()) continue;
                list_of_task.get(i - 1).markUnDone();

                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(list_of_task.get(i - 1).getDescription());
            }

            if (input.equals("list")) {
                if (list_of_task.isEmpty()) continue;
                System.out.println("Here are the tasks in your list: ");
                for (int index = 0; index < list_of_task.size(); index++)
                {
                    System.out.println(index+1 + ". " + list_of_task.get(index).toString());
                }
            }

            if (input.equals("bye") ) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

        }
        scanner.close();
    }

}