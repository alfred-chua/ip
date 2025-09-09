package luke;

/**
 * The Ui class stores common phrases for Luke
 * to interact with the user.
 */
public class Ui {

    protected String hello;
    protected String goodbye;

    public Ui() {
        this.hello = "Hello! I'm Luke\n"
                + "What can I do for you?";
        this.goodbye = "Bye. Hope to see you again soon!";
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(hello);
    }

    /**
     * Says goodbye to the user.
     */
    public void bye() {
        System.out.println(goodbye);
    }
}
