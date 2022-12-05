public enum TaskRepeatability {
        ONCE("однократно"),
        EVERY_DAY("каждый день"),
        EVERY_WEEK("каждую неделю"),
        EVERY_MONTH("каждый месяц"),
        EVERY_YEAR("каждый год");

        public static TaskRepeatability findTaskRepeatability(String taskRepeatabilityString) {
            for (TaskRepeatability repeatability : values()) {
                if (repeatability.getTaskRepeatabilityString().equals(taskRepeatabilityString)) {
                    return repeatability;
                }
            }
            throw new IllegalArgumentException("Необходимо указать значения: \"однократно\", \"каждый день\", \"каждую неделю\", \"каждый месяц\" или \"каждый год\"");
        }
        private String taskRepeatabilityString;

        TaskRepeatability(String taskRepeatabilityString) {
            if (taskRepeatabilityString != null && !taskRepeatabilityString.isEmpty() && !taskRepeatabilityString.isBlank()) {
                this.taskRepeatabilityString = taskRepeatabilityString;
            }
        }

        public String getTaskRepeatabilityString() {
            return taskRepeatabilityString;
        }
    }

