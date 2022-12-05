package TaskRepeatability;

import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(LocalDateTime taskTime) {
        super(taskTime);
    }

    @Override
    public LocalDateTime getTaskNextTime() {
        return getTaskTime().plusMonths(1);
    }
}
