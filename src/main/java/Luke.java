import java.util.ArrayList;

public class Luke {
    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage("tasks.txt");
        storage.initialize();
        ArrayList<Task> tasks = storage.tasks;

        ui.greet();

        Parser parser = new Parser("", tasks);
        parser.run();

        ui.bye();
    }
}
