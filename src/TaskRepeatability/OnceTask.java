package TaskRepeatability;

import java.time.LocalDateTime;

public class OnceTask extends Task {
    public OnceTask(LocalDateTime taskTime) {
        super(taskTime);
    }

    @Override
    public LocalDateTime getTaskNextTime() {
        return null;
    }
}
