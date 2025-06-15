import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    private String description;

    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime, String title, String description) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.description = description;
    }

    // Getter
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s~%s %s - %s",
                date, startTime, endTime, title, description);
    }
}
