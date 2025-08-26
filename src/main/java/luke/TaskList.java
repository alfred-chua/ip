package luke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    protected String input;

    public TaskList(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    public void listTasks() {
        this.input = "list";
        int len = tasks.size();
        if (len == 0) {
            System.out.println("You have no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < len; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void markTask(String input) {
        this.input = input;
        String strIndex = input.substring(5);
        int index;
        try {
            index = Integer.parseInt(strIndex);
            if (index <= 0 || index > tasks.size()) {
                System.out.println("Error: marked value cannot be negative or more than list size");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: marked value must be a number");
            return;
        }
        tasks.get(index - 1).complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
    }

    public void deleteTask(String input) {
        this.input = input;
        String strIndex = input.substring(7);
        int index;
        try {
            index = Integer.parseInt(strIndex);
            if (index <= 0 || index > tasks.size()) {
                System.out.println("Error: deleted value cannot be negative or more than list size");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: deleted value must be a number");
            return;
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void addTask(String input) {
        Task task;
        String description;
        this.input = input;

        if (input.startsWith("todo ")) {
            description = input.substring(5);
            if (description.replaceAll("\\s", "").isEmpty()) {
                System.out.println("Error: description cannot be empty");
                return;
            }
            task = new ToDo(description);
        } else if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf("/by ");
            if (byIndex == -1) {
                System.out.println("Error: luke.Deadline task must be specified with /by ");
                return;
            }
            description = input.substring(9, byIndex - 1);
            String by = input.substring(byIndex + 4);

            if (by.replaceAll("\\s", "").isEmpty()) {
                System.out.println("Error: by date cannot be empty");
                return;
            }
            LocalDate byDate = LocalDate.parse(by);
            task = new Deadline(description, byDate);
        } else {
            int fromIndex = input.indexOf("/from ");
            int toIndex = input.indexOf("/to ", fromIndex);
            if (fromIndex == -1 || toIndex == -1) {
                System.out.println("Error: luke.Event task must be specified with /from and /to");
                return;
            }
            description = input.substring(6, fromIndex - 1);
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);

            if (from.replaceAll("\\s", "").isEmpty()) {
                System.out.println("Error: from date cannot be empty");
                return;
            }
            if (to.replaceAll("\\s", "").isEmpty()) {
                System.out.println("Error: to date cannot be empty");
                return;
            }
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            task = new Event(description, fromDate, toDate);
        }

        if (description.replaceAll("\\s", "").isEmpty()) {
            System.out.println("Error: description cannot be empty");
            return;
        }

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

}
