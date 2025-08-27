package luke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String fromFormat = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toFormat = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + fromFormat + " to: " + toFormat + ")";
    }
}
