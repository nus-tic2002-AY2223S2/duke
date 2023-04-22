import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        ArrayList<String> current_task = null;
        try {
            Storage storage = new Storage();
            current_task = storage.ReadFile("src/main/task.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        // create an array of task
        ArrayList<Task> list_of_task = new ArrayList<Task>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) continue;
            String[] strArr = input.split(" ");

            try {
                if (!input.contains("list")
                        && !input.contains("bye")
                        && !input.contains("mark")
                        && !input.contains("unmark")
                        && !input.contains("delete")
                        && !input.contains("todo")
                        && !input.contains("deadline")
                        && !input.contains("event")
                        && !input.contains("find")
                        && !input.contains("undo")
                ) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                if ((strArr.length == 1) && (strArr[0].contains("todo"))) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }

                if (input.contains("undo")) {

                }

                if (input.matches("find(.*)")) {
                    String[] taskArray = Arrays.copyOfRange(strArr, 1, strArr.length);
                    String task = String.join(" ", taskArray);

                    try {
                        FileSearch fileSearch = new FileSearch();
                        fileSearch.parseFile(task);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (input.matches("event(.*)")) {
                    // find by/
                    int from = 0, to = 0;
                    String from_time = "/from", to_time = "/to";

                    for (int i = 0; i < input.length(); i++) {
                        if (from_time.equals(strArr[i])) {
                            from = i;
                            break;
                        }
                    }

                    for (int j = 0; j < input.length(); j++) {
                        if (to_time.equals(strArr[j])) {
                            to = j;
                            break;
                        }
                    }

                    String[] taskArray = Arrays.copyOfRange(strArr, 1, from);
                    String[] from_timingArray = Arrays.copyOfRange(strArr, from + 1, to);
                    String[] to_timingArray = Arrays.copyOfRange(strArr, to + 1, strArr.length);

                    String task = String.join(" ", taskArray);

                    String from_timing = String.join(" ", from_timingArray);

                    LocalDate date_format_from = LocalDate.parse(from_timing);
                    String format_from_timing = date_format_from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    String to_timing = String.join(" ", to_timingArray);

                    LocalDate date_format_to = LocalDate.parse(to_timing);
                    String format_to_timing = date_format_to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Event event = new Event(task, format_from_timing, format_to_timing);
// update
                    try {
                        Storage todo = new Storage();
                        todo.WriteFile(event.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    list_of_task.add(event);

                    System.out.println("Got it. I've added this task:\n" + event.toString());
                    System.out.println(String.format("Now you have %s tasks in the list.", current_task.size() + 1));
                }

                if (input.matches("deadline(.*)")) {
                    // find by/
                    int stop = 0;
                    String found = "/by";

                    for (int i = 0; i < input.length(); i++) {
                        if (found.equals(strArr[i])) {
                            stop = i;
                            break;
                        }
                    }
                    String[] taskArray = Arrays.copyOfRange(strArr, 1, stop);
                    String[] timingArray = Arrays.copyOfRange(strArr, stop + 1, strArr.length);

                    String task = String.join(" ", taskArray);
                    String timing = String.join(" ", timingArray);

                    LocalDate date_format = LocalDate.parse(timing);
                    String format_timing = date_format.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Deadline deadline = new Deadline(task, format_timing);

                    try {
                        Storage todo = new Storage();
                        todo.WriteFile(deadline.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    list_of_task.add(deadline);
                    System.out.println("Got it. I've added this task:\n" + deadline.toString());
                    System.out.println(String.format("Now you have %s tasks in the list.", current_task.size() + 1 ));
                }

                if (input.matches("todo(.*)")) {

                    String[] modifiedArray = Arrays.copyOfRange(strArr, 1, strArr.length);
                    String joinedString = String.join(" ", modifiedArray);

                    Todo to_do_task = new Todo(joinedString);

                    try {
                        Storage todo = new Storage();
                        todo.WriteFile(to_do_task.toString());
                        todo.copyFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    list_of_task.add(to_do_task);

                    System.out.println("Got it. I've added this task:\n" + to_do_task.toString());
                    System.out.println(String.format("Now you have %s tasks in the list.", current_task.size() + 1));
                }


                if (input.matches("mark(.*)")) {
                    String num_input = input.replaceAll("\\D+", "");
                    int i = Integer.parseInt(num_input);

                    if (i > current_task.size()) continue;

                    list_of_task.get(i - 1).markDone();

//                    try {
//                        Storage todo = new Storage();
//                        todo.RewriteFile("[ ]", "x");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(list_of_task.get(i - 1).getDescription());
                }

                if (input.matches("unmark(.*)")) {
                    String num_input = input.replaceAll("\\D+", "");
                    int i = Integer.parseInt(num_input);

                    if (i > list_of_task.size()) continue;
                    list_of_task.get(i - 1).markUnDone();

                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(list_of_task.get(i - 1).getDescription());
                }

                if (input.matches("delete(.*)")) {
                    String num_input = input.replaceAll("\\D+", "");
                    int i = Integer.parseInt(num_input);

                    if (i > current_task.size()) continue;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(current_task.get(i - 1).toString());
                    current_task.remove(i - 1);

                    if (!list_of_task.isEmpty())
                        System.out.println(String.format("Now you have %s tasks in the list.", current_task.size()));
                }

                if (input.equals("list")) {
                    if (current_task.isEmpty()) continue;
                    System.out.println("Here are the tasks in your list: ");

                    try {
                        Storage storage = new Storage();
                        current_task = storage.ReadFile("src/main/task.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    for (int index = 0; index < current_task.size(); index++) {
                        System.out.println((index + 1) + ". " + current_task.get(index).toString());
                    }
                }

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                }

            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        scanner.close();
    }
}