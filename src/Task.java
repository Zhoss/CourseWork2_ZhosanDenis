import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String headline;
    private String description;
    private TaskType taskType;
    private LocalDateTime taskTime;
    private final int id;
    private static int taskCounter = 0;

    public Task(String headline, String description, String taskType, int year, int month, int day, int hour, int minute) {
        setHeadline(headline);
        setDescription(description);
        setTaskType(taskType);
        setTaskTime(year, month, day, hour, minute);
        taskCounter++;
        this.id = taskCounter;
    }

    public abstract LocalDateTime getTaskNextTime();

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskType() {
        return taskType.getTaskTypeString();
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public int getId() {
        return id;
    }

    public void setHeadline(String headline) {
        if (headline != null && !headline.isEmpty() && !headline.isBlank()) {
            this.headline = headline;
        } else {
            throw new IllegalArgumentException("Требуется заполнить заголовок задачи");
        }
    }

    public void setDescription(String description) {
        if (description != null && !description.isEmpty() && !description.isBlank()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Требуется заполнить описание задачи");
        }
    }

    public void setTaskType(String taskType) {
        if (taskType != null && !taskType.isEmpty() && !taskType.isBlank()) {
            this.taskType = TaskType.findTaskType(taskType);
        }
    }

    public void setTaskTime(int year, int month, int day, int hour, int minute) {
        if (year > 2020 && year <= 2100 && month >= 1 && month <= 12 && day >= 1 && day <= 31 && hour >= 0 && hour <= 23 && minute >= 0 && minute < 60) {
            if (LocalDateTime.of(year, month, day, hour, minute).isAfter(LocalDateTime.now())) {
                this.taskTime = LocalDateTime.of(year, month, day, hour, minute);
            } else {
                throw new IllegalArgumentException("Задача не может быть создана ранее нынешних даты и времени");
            }
        } else {
            throw new IllegalArgumentException("Укажите корректные дату и время");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(headline, task.headline) && Objects.equals(description, task.description) && taskType == task.taskType && Objects.equals(taskTime, task.taskTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, description, taskType, taskTime, id);
    }

    @Override
    public String toString() {
        return "Задача " + getHeadline() +
                ", описание: " + getDescription() +
                ", тип задачи " + getTaskType() +
                ", дата и время задачи " + getTaskTime();
    }
}
