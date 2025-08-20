import java.util.Objects;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Luke \n"
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

            } else if (!Objects.equals(input, "")) {
                 tasks[len] = new Task(input);
                 len++;
                 System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println(goodbye);
    }
}
