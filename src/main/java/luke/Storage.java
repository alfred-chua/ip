package luke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.util.Objects.isNull;

/**
 * The Storage class stores the array of tasks
 * and the file (tasks.txt) where the TaskList is stored.
 */
public class Storage {

    protected String filePath;
    protected ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes list of tasks with data from tasks.txt.
     * If tasks.txt does not yet exist, it is created.
     */
    public void initialize() {
        try {
            File taskFile = new File("tasks.txt");
            Scanner reader = new Scanner(taskFile);
            String[] parts;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                parts = data.split("\\|");
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
                    task.isCompleted = true;
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            File taskFile = new File("tasks.txt");
            System.out.println("File created: " + taskFile.getName());
        }
    }

    /**
     * Saves the list of tasks into the file tasks.txt
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter("tasks.txt");
            for (Task task: tasks) {
                String type;
                int isCompleted;
                if (task.isCompleted) {
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
    }
}
