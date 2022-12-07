import java.time.LocalDateTime;
import java.util.Objects;

public class DailyTask extends Task {
    private final String repeatability;

    public DailyTask(String headline, String description, String taskType, int year, int month, int day, int hour, int minute) {
        super(headline, description, taskType, year, month, day, hour, minute);
        this.repeatability = "каждый день";
    }

    @Override
    public LocalDateTime getTaskNextTime() {
        return getTaskTime().plusDays(1);
    }

    public String getRepeatability() {
        return repeatability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DailyTask dailyTask = (DailyTask) o;
        return Objects.equals(repeatability, dailyTask.repeatability);
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
