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

                TaskList tasklist = new TaskList(input, tasks);
                tasklist.addTask();
                tasks = tasklist.tasks;

            } else if (!Objects.equals(input, "")) {
                System.out.println("Error: unknown command");
                input = "";
                continue;
            }

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
