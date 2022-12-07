import java.time.LocalDateTime;
import java.util.Objects;

public class WeeklyTask extends Task {
    private final String repeatability;

    public WeeklyTask(String headline, String description, String taskType, int year, int month, int day, int hour, int minute) {
        super(headline, description, taskType, year, month, day, hour, minute);
        this.repeatability = "каждую неделю";
    }

    @Override
    public LocalDateTime getTaskNextTime() {
        return getTaskTime().plusWeeks(1);
    }

    public String getRepeatability() {
        return repeatability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WeeklyTask that = (WeeklyTask) o;
        return Objects.equals(repeatability, that.repeatability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), repeatability);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", повторяемость задачи " + getRepeatability();
    }
}
