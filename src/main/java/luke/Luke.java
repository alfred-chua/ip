package luke;

import java.util.ArrayList;

public class Luke {

    protected final Ui ui;
    protected final Parser parser;

    public Luke() {
        Storage storage = new Storage("tasks.txt");
        storage.initialize();
        ArrayList<Task> tasks = storage.tasks;

        this.ui = new Ui();
        this.parser = new Parser("", tasks);
    }

    public void run() {
        ui.greet();
        parser.run();
        ui.bye();
    }

    public static void main(String[] args) {
        new Luke().run();
    }
}
