import java.io.FileWriter;
import java.io.IOException;
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

            try {
                FileWriter writer = new FileWriter("tasks.txt");
                for (Task task: tasks) {
                    String type;
                    int isCompleted;
                    if (task.completed) {
                        isCompleted = 1;
                    } else {
                        isCompleted = 0;
                    }
                    if (task instanceof ToDo) {
                        type = "T";
                        writer.write(type + "|" + isCompleted + "|" + task.description + "\n");
                    } else if (task instanceof Deadline) {
                        type = "D";
                        writer.write(type + "|" + isCompleted + "|" + task.description +
                                "|" + ((Deadline) task).by + "\n");
                    } else {
                        type = "E";
                        writer.write(type + "|" + isCompleted + "|" + task.description +
                                "|" + ((Event) task).from + "|" + ((Event) task).to + "\n");
                    }
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }

            input = sc.nextLine();
        }
    }
}
