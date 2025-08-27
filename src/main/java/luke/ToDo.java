package luke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
