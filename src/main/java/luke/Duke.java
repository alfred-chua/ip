package luke;

import java.util.ArrayList;

public class Duke {

    protected final Ui ui;
    protected final Parser parser;
    protected final ArrayList<Task> tasks;

    public Duke() {
        Storage storage = new Storage("tasks.txt");
        storage.initialize();
        this.tasks = storage.tasks;

        this.ui = new Ui();
        this.parser = new Parser("", tasks);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Duke duke = new Duke();
        Parser parser = new Parser(input, duke.tasks);
        return parser.runGui();
    }
}

