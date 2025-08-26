import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Parser {

    protected String input;
    protected ArrayList<Task> tasks;

    public Parser(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        TaskList tasklist = new TaskList(input, tasks);
        while (!Objects.equals(input, "bye")) {

            if (Objects.equals(input, "list")) {
                tasklist.listTasks();

            } else if (input.startsWith("mark ")) {
                tasklist.markTask(input);

            } else if (input.startsWith("delete ")) {
                tasklist.deleteTask(input);

            } else if (input.startsWith("todo ") ||
                    input.startsWith("deadline ") ||
                    input.startsWith("event ")) {
                tasklist.addTask(input);

            } else if (!Objects.equals(input, "")) {
                System.out.println("Error: unknown command");
                input = "";
                continue;
            }
            tasks = tasklist.tasks;

            Storage storage = new Storage("tasks.txt");
            storage.tasks = tasks;
            storage.save();

            input = sc.nextLine();
        }
    }
}
