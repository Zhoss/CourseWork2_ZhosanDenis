import java.time.LocalDateTime;
import java.util.Objects;
import TaskRepeatability.*;

public class DiaryTask {
    private String headline;
    private String description;
    private TaskType taskType;
    private LocalDateTime taskTime;
    private TaskRepeatability repeatability;
    private Task task;
    private final int id;
    private static int taskCounter = 0;

    public DiaryTask(String headline, String description, String taskType, int year, int month, int day, int hour, int minute, String repeatability) {
        setHeadline(headline);
        setDescription(description);
        setTaskType(taskType);
        setTaskTime(year, month, day, hour, minute);
        setRepeatability(repeatability);
        setTask(repeatability);
        taskCounter++;
        this.id = taskCounter;
    }

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

    public String getRepeatability() {
        return repeatability.getTaskRepeatabilityString();
    }

    public Task getTask() {
        return task;
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

    public void setRepeatability(String repeatability) {
        if (repeatability != null && !repeatability.isEmpty() && !repeatability.isBlank()) {
            this.repeatability = TaskRepeatability.findTaskRepeatability(repeatability);
        }
    }

    public void setTask(String taskRepeatability) {
        if (taskRepeatability.equals(TaskRepeatability.ONCE.getTaskRepeatabilityString())) {
            this.task = new OnceTask(getTaskTime());
        } else if (taskRepeatability.equals(TaskRepeatability.EVERY_DAY.getTaskRepeatabilityString())) {
            this.task = new DailyTask(getTaskTime());
        } else if (taskRepeatability.equals(TaskRepeatability.EVERY_WEEK.getTaskRepeatabilityString())) {
            this.task = new WeeklyTask(getTaskTime());
        } else if (taskRepeatability.equals(TaskRepeatability.EVERY_MONTH.getTaskRepeatabilityString())) {
            this.task = new MonthlyTask(getTaskTime());
        } else if (taskRepeatability.equals(TaskRepeatability.EVERY_YEAR.getTaskRepeatabilityString())) {
            this.task = new YearlyTask(getTaskTime());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryTask diaryTask = (DiaryTask) o;
        return id == diaryTask.id && Objects.equals(headline, diaryTask.headline) && Objects.equals(description, diaryTask.description) && taskType == diaryTask.taskType && Objects.equals(taskTime, diaryTask.taskTime) && repeatability == diaryTask.repeatability && Objects.equals(task, diaryTask.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, description, taskType, taskTime, repeatability, task, id);
    }

    @Override
    public String toString() {
        return "Задача " + getHeadline() +
                ", описание: " + getDescription() +
                ", тип задачи " + getTaskType() +
                ", дата и время задачи " + getTaskTime() +
                ", повторяемость задачи " + getRepeatability();
    }
}
