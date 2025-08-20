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
        int len = 0;

        System.out.println(greeting);

        while (!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                for (int i = 1; i <= len; i++) {
                    System.out.println(i + ". " + texts[i - 1]);
                }
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
