import java.util.Objects;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Luke \n"
                + "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!";

        System.out.println(greeting);
        String input = "";

         while (!Objects.equals(input, "bye")) {
             System.out.println(input);
             input = sc.nextLine();
         }

        System.out.println(goodbye);
    }
}
