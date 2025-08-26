package luke;

public class Task {

    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
    }

    public String toString() {
        if (this.completed) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
