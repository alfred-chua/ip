import java.util.Objects;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Luke\n"
                + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        String input = "";
        Task[] tasks = new Task[100];
        int len = 0;

        System.out.println(greeting);

        while (!Objects.equals(input, "bye")) {

            if (Objects.equals(input, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < len; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }

            } else if (input.startsWith("mark ")) {
                String strIndex = input.substring(5);
                int index = Integer.parseInt(strIndex);
                tasks[index - 1].complete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index - 1]);

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
                    task = new Event(description, from, to);
                }

                if (description.replaceAll("\\s", "").isEmpty()) {
                    System.out.println("Error: description cannot be empty");
                    input = "";
                    continue;
                }

                tasks[len] = task;
                len++;
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + len + " tasks in the list.");

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
