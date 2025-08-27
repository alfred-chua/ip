package luke;

public class Task {

    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
