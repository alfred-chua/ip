package luke;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * The parser class parses user input.
 */
public class Parser {

    protected String input;
    protected ArrayList<Task> tasks;

    public Parser(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Main function for handling user input.
     * Runs until user types "bye".
     * Known commands: list, mark [task number], delete [task number],
     *  todo [description], deadline [description] /by [YYYY-MM-DD],
     *  event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]
     */
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

            } else if (input.startsWith("find ")) {
                tasklist.findTasks(input);

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

    /**
     * Main function for handling user input.
     * Runs until user types "bye".
     * Known commands: list, mark [task number], delete [task number],
     *  todo [description], deadline [description] /by [YYYY-MM-DD],
     *  event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]
     */
    public String runGui() {
        String response;
        TaskList tasklist = new TaskList(input, tasks);

        if (Objects.equals(input, "list")) {
            response = tasklist.listTasksGui();

        } else if (input.startsWith("mark ")) {
            response = tasklist.markTaskGui(input);

        } else if (input.startsWith("delete ")) {
            response = tasklist.deleteTaskGui(input);

        } else if (input.startsWith("find ")) {
            response = tasklist.findTasksGui(input);

        } else if (input.startsWith("todo ") ||
                input.startsWith("deadline ") ||
                input.startsWith("event ")) {
            response = tasklist.addTaskGui(input);

        } else if (Objects.equals(input, "bye")) {
            response = "Bye. Hope to see you again soon!";

        } else if (!Objects.equals(input, "")) {
            response = "Error: unknown command";
            input = "";

        } else {
            response = "";
        }
        tasks = tasklist.tasks;

        Storage storage = new Storage("tasks.txt");
        storage.tasks = tasks;
        storage.save();
        return response;
    }
}
