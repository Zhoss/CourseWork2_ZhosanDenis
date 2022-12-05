package TaskRepeatability;
import java.time.LocalDateTime;

public abstract class Task {
    private LocalDateTime taskTime;

    public Task(LocalDateTime taskTime) {
        this.taskTime = taskTime;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public abstract LocalDateTime getTaskNextTime();
}
