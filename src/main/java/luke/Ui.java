package luke;

public class Ui {

    protected String hello;
    protected String goodbye;

    public Ui() {
        this.hello = "Hello! I'm Luke\n"
                + "What can I do for you?";
        this.goodbye = "Bye. Hope to see you again soon!";
    }

    public void greet() {
        System.out.println(hello);
    }

    public void bye() {
        System.out.println(goodbye);
    }
}
