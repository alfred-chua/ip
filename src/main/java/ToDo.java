public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.completed = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
