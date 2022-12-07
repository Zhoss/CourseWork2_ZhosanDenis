public enum TaskType {
    PERSONAL_TASK("личная"),
    WORK_TASK("рабочая");

    public static TaskType findTaskType(String taskTypeString) {
        for (TaskType taskType : values()) {
            if (taskType.getTaskTypeString().equals(taskTypeString)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException("Необходимо установить значение \"личная\" или \"рабочая\"");
    }

    private String taskTypeString;

    TaskType(String taskTypeString) {
        if (taskTypeString != null && !taskTypeString.isEmpty() && !taskTypeString.isBlank()) {
            this.taskTypeString = taskTypeString;
        }
    }

    public String getTaskTypeString() {
        return taskTypeString;
    }
}
