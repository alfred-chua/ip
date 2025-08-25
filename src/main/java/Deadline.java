import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.completed = false;
    }

    @Override
    public String toString() {
        String byFormat = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + byFormat + ")";
    }
}
