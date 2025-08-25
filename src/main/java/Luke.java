import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import static java.util.Objects.isNull;

public class Luke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Luke\n"
                + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        String input = "";
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File taskFile = new File("tasks.txt");
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] parts = data.split("\\|");
                Task task;

                if (Objects.equals(parts[0], "T")) {
                    task = new ToDo(parts[2]);
                } else if (Objects.equals(parts[0], "D")) {
                    LocalDate byDate = LocalDate.parse(parts[3]);
                    task = new Deadline(parts[2], byDate);
                } else if (Objects.equals(parts[0], "E")) {
                    LocalDate fromDate = LocalDate.parse(parts[3]);
                    LocalDate toDate = LocalDate.parse(parts[4]);
                    task = new Event(parts[2], fromDate, toDate);
                } else {
                    task = null;
                    System.out.println("Error: task type not recognized when reading file");
                }
                if (Objects.equals(parts[1], "1") && !isNull(task)) {
                    task.completed = true;
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            File taskFile = new File("tasks.txt");
            System.out.println("File created: " + taskFile.getName());
        }
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
                    description = input.substring(9, byIndex - 1);
                    String by = input.substring(byIndex + 4);

                    if (by.replaceAll("\\s", "").isEmpty()) {
                        System.out.println("Error: by date cannot be empty");
                        input = "";
                        continue;
                    }
                    LocalDate byDate = LocalDate.parse(by);
                    task = new Deadline(description, byDate);
                } else {
                    int fromIndex = input.indexOf("/from ");
                    int toIndex = input.indexOf("/to ", fromIndex);
                    if (fromIndex == -1 || toIndex == -1) {
                        System.out.println("Error: Event task must be specified with /from and /to");
                        input = "";
                        continue;
                    }
                    description = input.substring(6, fromIndex - 1);
                    String from = input.substring(fromIndex + 6, toIndex - 1);
                    String to = input.substring(toIndex + 4);

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
                    LocalDate fromDate = LocalDate.parse(from);
                    LocalDate toDate = LocalDate.parse(to);
                    task = new Event(description, fromDate, toDate);
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
        System.out.println(goodbye);
    }
}
