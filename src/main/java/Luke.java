import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Luke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Luke\n"
                + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        String input = "";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(greeting);

        while (!Objects.equals(input, "bye")) {

            if (Objects.equals(input, "list")) {
                int len = tasks.size();
                if (len == 0) {
                    System.out.println("You have no tasks in your list");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < len; i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }


            } else if (input.startsWith("mark ")) {
                String strIndex = input.substring(5);
                int index;
                try {
                    index = Integer.parseInt(strIndex);
                    if (index <= 0 || index > tasks.size()) {
                        System.out.println("Error: marked value cannot be negative or more than list size");
                        input = "";
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: marked value must be a number");
                    input = "";
                    continue;
                }
                tasks.get(index - 1).complete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index - 1));

            } else if (input.startsWith("delete ")) {
                String strIndex = input.substring(7);
                int index;
                try {
                    index = Integer.parseInt(strIndex);
                    if (index <= 0 || index > tasks.size()) {
                        System.out.println("Error: deleted value cannot be negative or more than list size");
                        input = "";
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: deleted value must be a number");
                    input = "";
                    continue;
                }
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(index - 1));
                tasks.remove(index - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list");

            } else if (input.startsWith("todo ") ||
                        input.startsWith("deadline ") ||
                        input.startsWith("event ")) {
                Task task;
                String description;

                if (input.startsWith("todo ")) {
                    description = input.substring(5);
                    if (description.replaceAll("\\s", "").isEmpty()) {
                        System.out.println("Error: description cannot be empty");
                        input = "";
                        continue;
                    }
                    task = new ToDo(description);
                } else if (input.startsWith("deadline ")) {
                    int byIndex = input.indexOf("/by ");
                    if (byIndex == -1) {
                        System.out.println("Error: Deadline task must be specified with /by ");
                        input = "";
                        continue;
                    }
                    description = input.substring(9, byIndex);
                    String by = input.substring(byIndex + 3);

                    if (by.replaceAll("\\s", "").isEmpty()) {
                        System.out.println("Error: by date cannot be empty");
                        input = "";
                        continue;
                    }
                    task = new Deadline(description, by);
                } else {
                    int fromIndex = input.indexOf("/from ");
                    int toIndex = input.indexOf("/to ", fromIndex);
                    if (fromIndex == -1 || toIndex == -1) {
                        System.out.println("Error: Event task must be specified with /from and /to");
                        input = "";
                        continue;
                    }
                    description = input.substring(6, fromIndex);
                    String from = input.substring(fromIndex + 5, toIndex);
                    String to = input.substring(toIndex + 3);

                    if (from.replaceAll("\\s", "").isEmpty()) {
                        System.out.println("Error: from date cannot be empty");
                        input = "";
                        continue;
                    }
                    if (to.replaceAll("\\s", "").isEmpty()) {
                        System.out.println("Error: to date cannot be empty");
                        input = "";
                        continue;
                    }
                    task = new Event(description, from, to);
                }

                if (description.replaceAll("\\s", "").isEmpty()) {
                    System.out.println("Error: description cannot be empty");
                    input = "";
                    continue;
                }

                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            } else if (!Objects.equals(input, "")) {
                 System.out.println("Error: unknown command");
                 input = "";
                 continue;
            }
            input = sc.nextLine();
        }
        System.out.println(goodbye);
    }
}
