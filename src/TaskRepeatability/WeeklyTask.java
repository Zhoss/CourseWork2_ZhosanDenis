package TaskRepeatability;

import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(LocalDateTime taskTime) {
        super(taskTime);
    }

    @Override
    public LocalDateTime getTaskNextTime() {
        return getTaskTime().plusWeeks(1);
    }
}
