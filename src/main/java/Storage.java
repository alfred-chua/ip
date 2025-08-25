import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Storage {

    protected String filePath;
    protected ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

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
                    task.completed = true;
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            File taskFile = new File("tasks.txt");
            System.out.println("File created: " + taskFile.getName());
        }
    }
}
