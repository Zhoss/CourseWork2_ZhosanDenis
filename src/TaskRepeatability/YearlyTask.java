package TaskRepeatability;

import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(LocalDateTime taskTime) {
        super(taskTime);
    }

    @Override
    public LocalDateTime getTaskNextTime() {
        return getTaskTime().plusYears(1);
    }
}
