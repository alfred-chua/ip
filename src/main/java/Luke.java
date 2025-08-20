import java.util.Objects;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Luke \n"
                + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        String input = "";
        String[] texts = new String[100];
        boolean[] done = new boolean[100];
        for (int i = 0; i < 100; i++) {
            done[i] = false;
        }
        int len = 0;

        System.out.println(greeting);

        while (!Objects.equals(input, "bye")) {

            if (Objects.equals(input, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= len; i++) {
                    if (done[i - 1]) {
                        System.out.println(i + ".[X] " + texts[i - 1]);
                    } else {
                        System.out.println(i + ".[ ] " + texts[i - 1]);
                    }
                }

            } else if (input.startsWith("mark ")) {
                String strIndex = input.substring(5);
                int index = Integer.parseInt(strIndex);
                done[index - 1] = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + texts[index - 1]);

            } else if (!Objects.equals(input, "")) {
                 texts[len] = input;
                 len++;
                 System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println(goodbye);
    }
}
