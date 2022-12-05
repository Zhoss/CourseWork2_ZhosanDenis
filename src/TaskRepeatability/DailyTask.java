package TaskRepeatability;

import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(LocalDateTime taskTime) {
        super(taskTime);
    }

    @Override
    public LocalDateTime getTaskNextTime() {
        return getTaskTime().plusDays(1);
    }
}
