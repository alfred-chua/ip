package luke;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    protected String input;

    public TaskList(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Prints out list of tasks.
     * If the list of tasks is empty, no tasks is printed.
     */
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

    /**
     * Marks task number given by user as completed.
     * @param input user input: "mark [task number]"
     */
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

    /**
     * Deletes task number given by user
     * @param input user input: "delete [task number]".
     */
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

    public void findTasks(String input) {
        this.input = input;
        String searchedWord = input.substring(5);
        System.out.println("Here are the matching tasks in your list:");
        int index = 0;
        for (Task task: tasks) {
            if (task.description.contains(searchedWord)) {
                index++;
                System.out.println(index + ". " + task);
            }
        }
        if (index == 0) {
            System.out.println("No tasks matched your search.");
        }
    }

    /**
     * Adds task given by user to list of tasks
     * Task must be a Todo, Deadline, or Event.
     * Deadline task must include /by [date in YYYY-MM-DD]
     * Event task must include /from [date] and /to [date]
     * @param input user input: E.g: "todo [description]"
     */
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



    /**
     * Prints out list of tasks.
     * If the list of tasks is empty, no tasks is printed.
     */
    public String listTasksGui() {
        this.input = "list";
        int len = tasks.size();
        if (len == 0) {
            return "You have no tasks in your list";
        } else {
            String response = "Here are the tasks in your list:\n";
            for (int i = 0; i < len; i++) {
                response += (i + 1) + ". " + tasks.get(i) + "\n";
            }
            return response;
        }
    }

    /**
     * Marks task number given by user as completed.
     * @param input user input: "mark [task number]"
     */
    public String markTaskGui(String input) {
        this.input = input;
        String strIndex = input.substring(5);
        int index;
        try {
            index = Integer.parseInt(strIndex);
            if (index <= 0 || index > tasks.size()) {
                return "Error: marked value cannot be negative or more than list size";
            }
        } catch (NumberFormatException e) {
            return "Error: marked value must be a number";
        }
        tasks.get(index - 1).complete();
        return "Nice! I've marked this task as done:\n"
                + tasks.get(index - 1);
    }

    /**
     * Deletes task number given by user
     * @param input user input: "delete [task number]".
     */
    public String deleteTaskGui(String input) {
        this.input = input;
        String strIndex = input.substring(7);
        int index;
        try {
            index = Integer.parseInt(strIndex);
            if (index <= 0 || index > tasks.size()) {
                return "Error: deleted value cannot be negative or more than list size";
            }
        } catch (NumberFormatException e) {
            return "Error: deleted value must be a number";
        }

        String response = "Noted. I've removed this task:\n"
                + tasks.get(index - 1);
        tasks.remove(index - 1);
        response += "Now you have " + tasks.size() + " tasks in the list";
        return response;
    }

    public String findTasksGui(String input) {
        this.input = input;
        String searchedWord = input.substring(5);
        String response = "Here are the matching tasks in your list:\n";
        int index = 0;
        for (Task task: tasks) {
            if (task.description.contains(searchedWord)) {
                index++;
                response += index + ". " + task + "\n";
            }
        }
        if (index == 0) {
            return "No tasks matched your search.";
        }
        return response;
    }

    /**
     * Adds task given by user to list of tasks
     * Task must be a Todo, Deadline, or Event.
     * Deadline task must include /by [date in YYYY-MM-DD]
     * Event task must include /from [date] and /to [date]
     * @param input user input: E.g: "todo [description]"
     */
    public String addTaskGui(String input) {
        Task task;
        String description;
        this.input = input;

        if (input.startsWith("todo ")) {
            description = input.substring(5);
            if (description.replaceAll("\\s", "").isEmpty()) {
                return "Error: description cannot be empty";
            }
            task = new ToDo(description);
        } else if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf("/by ");
            if (byIndex == -1) {
                return "Error: Deadline task must be specified with /by ";
            }
            description = input.substring(9, byIndex - 1);
            String by = input.substring(byIndex + 4);

            if (by.replaceAll("\\s", "").isEmpty()) {
                return "Error: by date cannot be empty";
            }
            try {
                LocalDate byDate = LocalDate.parse(by);
                task = new Deadline(description, byDate);
            } catch (Exception e) {
                return "Error: byDate not in YYYY-MM-DD format";
            }

        } else {
            int fromIndex = input.indexOf("/from ");
            int toIndex = input.indexOf("/to ", fromIndex);
            if (fromIndex == -1 || toIndex == -1) {
                return "Error: Event task must be specified with /from and /to";
            }
            description = input.substring(6, fromIndex - 1);
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);

            if (from.replaceAll("\\s", "").isEmpty()) {
                return "Error: from date cannot be empty";
            }
            if (to.replaceAll("\\s", "").isEmpty()) {
                return "Error: to date cannot be empty";
            }
            try {
                LocalDate fromDate = LocalDate.parse(from);
                LocalDate toDate = LocalDate.parse(to);
                task = new Event(description, fromDate, toDate);
            } catch (Exception e) {
                return "Error: fromDate or toDate not in YYYY-MM-DD format";
            }
        }

        if (description.replaceAll("\\s", "").isEmpty()) {
            return "Error: description cannot be empty";
        }

        tasks.add(task);
        return "Got it. I've added this task:\n" +
                task + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.";
    }

}
