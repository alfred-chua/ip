import java.util.ArrayList;

public class Luke {
    public static void main(String[] args) {

        String greeting = "Hello! I'm Luke\n"
                + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        Storage storage = new Storage("tasks.txt");
        storage.initialize();
        ArrayList<Task> tasks = storage.tasks;

        System.out.println(greeting);

        Parser parser = new Parser("", tasks);
        parser.run();

        System.out.println(goodbye);
    }
}
