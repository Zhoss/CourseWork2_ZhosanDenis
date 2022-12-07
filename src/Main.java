import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Task> diary = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner, diary);
                            break;
                        case 2:
                            deleteTask(scanner, diary);
                            break;
                        case 3:
                            findTask(scanner, diary);
                            break;
                        case 4:
                            editTask(scanner, diary);
                            break;
                        case 5:
                            printSortedByDateTasks(diary);
                            break;
                        case 6:
                            printDeletedTasks(diary);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner, Map<Integer, Task> map) {
        scanner.useDelimiter("\n");

        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        if (taskName.isBlank()) {
            System.out.println("Необходимо ввести название задачи!!!");
            scanner.close();
        }

        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.next();
        if (taskDescription.isBlank()) {
            System.out.println("Необходимо ввести описание задачи!!!");
            scanner.close();
        }

        System.out.print("Введите тип задачи (1 - личная/2 - рабочая): ");
        String taskType = null;
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    taskType = "личная";
                    break;
                case 2:
                    taskType = "рабочая";
                    break;
                default:
                    System.out.println("Данные введены некорректно");
                    scanner.close();
            }
        } else {
            System.out.println("Необходимо ввести тип задачи(личная/рабочая)!!!");
            scanner.close();
        }

        System.out.print("Введите дату выполнения (год): ");
        int taskYear = 0;
        if (scanner.hasNextInt()) {
            taskYear = scanner.nextInt();
            if (taskYear <= 2020 || taskYear > 2100) {
                System.out.println("Дата (год) должна быть числом");
                scanner.close();
            }
        }

        System.out.print("Введите дату выполнения (месяц): ");
        int taskMonth = 0;
        if (scanner.hasNextInt()) {
            taskMonth = scanner.nextInt();
            if (taskMonth < 1 || taskMonth > 12) {
                System.out.println("Дата (месяц) должна быть числом");
                scanner.close();
            }
        }

        System.out.print("Введите дату выполнения (день месяца): ");
        int taskDay = 0;
        if (scanner.hasNextInt()) {
            taskDay = scanner.nextInt();
            if (taskDay < 1 || taskDay > 31) {
                System.out.println("Дата (день месяца) должна быть числом");
                scanner.close();
            }
        }

        System.out.print("Введите время выполнения (час): ");
        int taskHour = 0;
        if (scanner.hasNextInt()) {
            taskHour = scanner.nextInt();
            if (taskHour < 0 || taskHour > 23) {
                System.out.println("Время (час) должно быть числом");
                scanner.close();
            }
        }

        System.out.print("Введите время выполнения (минуты): ");
        int taskMinute = 0;
        if (scanner.hasNextInt()) {
            taskMinute = scanner.nextInt();
            if (taskMinute < 0 || taskMinute >= 60) {
                System.out.println("Время (минуты) должно быть числом");
                scanner.close();
            }
        }

        System.out.print("Введите повторяемость задачи (1 - однократно/2 - каждый день/3 - каждую неделю/4 - каждый месяц/5 - каждый год): ");
        String taskRepeatability = null;
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    taskRepeatability = "однократно";
                    break;
                case 2:
                    taskRepeatability = "каждый день";
                    break;
                case 3:
                    taskRepeatability = "каждую неделю";
                    break;
                case 4:
                    taskRepeatability = "каждый месяц";
                    break;
                case 5:
                    taskRepeatability = "каждый год";
                    break;
                default:
                    System.out.println("Данные введены некорректно");
                    scanner.close();
            }
        } else {
            System.out.println("Необходимо ввести повторяемость задачи (однократно/каждый день/каждую неделю/каждый месяц/каждый год)");
            scanner.close();
        }

        Task task = null;
        if (taskRepeatability.equals("однократно")) {
            task = new OnceTask(taskName, taskDescription, taskType, taskYear, taskMonth, taskDay, taskHour, taskMinute);
        } else if (taskRepeatability.equals("каждый день")) {
            task = new DailyTask(taskName, taskDescription, taskType, taskYear, taskMonth, taskDay, taskHour, taskMinute);
        } else if (taskRepeatability.equals("каждую неделю")) {
            task = new WeeklyTask(taskName, taskDescription, taskType, taskYear, taskMonth, taskDay, taskHour, taskMinute);
        } else if (taskRepeatability.equals("каждый месяц")) {
            task = new MonthlyTask(taskName, taskDescription, taskType, taskYear, taskMonth, taskDay, taskHour, taskMinute);
        } else if (taskRepeatability.equals("каждый год")) {
            task = new YearlyTask(taskName, taskDescription, taskType, taskYear, taskMonth, taskDay, taskHour, taskMinute);
        }
        assert task != null;
        map.put(task.getId(), task);
    }

    public static void deleteTask(Scanner scanner, Map<Integer, Task> map) {
        System.out.println("Все существующие задачи:");
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Task> taskEntry : map.entrySet()) {
            if (!taskEntry.getValue().isDeleted()) {
                builder.append("id = ").append(taskEntry.getKey()).append(" --> ").append(taskEntry.getValue());
                builder.append("\n");
            }
        }
        System.out.println(builder);

        System.out.print("Введите id задачи, которую хотите удалить (0 - выход): ");
        int taskID;
        if (scanner.hasNextInt()) {
            taskID = scanner.nextInt();
            if (taskID == 0) {
                System.out.println("\n");
            } else if (map.containsKey(taskID)) {
                map.get(taskID).setDeleted(true);
                System.out.println("Задача с ID = " + taskID + " удалена");
            } else {
                System.out.println("ID задачи не найдено");
                deleteTask(scanner, map);
            }
        } else {
            System.out.println("ID задачи указан некорректно");
            scanner.close();
        }
    }

    public static void findTask(Scanner scanner, Map<Integer, Task> map) {
        scanner.useDelimiter("\n");

        System.out.print("Введите дату искомой задачи (год): ");
        int taskYear = 0;
        if (scanner.hasNextInt()) {
            taskYear = scanner.nextInt();
            if (taskYear <= 2020 || taskYear > 2100) {
                System.out.println("Дата (год) должна быть числом");
                scanner.close();
            }
        }

        System.out.print("Введите дату искомой задачи (месяц): ");
        int taskMonth = 0;
        if (scanner.hasNextInt()) {
            taskMonth = scanner.nextInt();
            if (taskMonth < 1 || taskMonth > 12) {
                System.out.println("Дата (месяц) должна быть числом");
                scanner.close();
            }
        }

        System.out.print("Введите дату искомой задачи (день месяца): ");
        int taskDay = 0;
        if (scanner.hasNextInt()) {
            taskDay = scanner.nextInt();
            if (taskDay < 1 || taskDay > 31) {
                System.out.println("Дата (день месяца) должна быть числом");
                scanner.close();
            }
        }

        int taskCounter = 0;
        printDelimiter();
        for (Task task : map.values()) {
            if (task.getTaskTime().toLocalDate().equals(LocalDate.of(taskYear, taskMonth, taskDay))) {
                System.out.println(task.getHeadline() + ", " + task.getDescription());
                taskCounter++;
            }
        }
        System.out.println("Количество найденных задач: " + taskCounter);
        printDelimiter();
    }

    public static void editTask(Scanner scanner, Map<Integer, Task> map) {
        System.out.println("Все существующие задачи:");
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Task> taskEntry : map.entrySet()) {
            if (!taskEntry.getValue().isDeleted()) {
                builder.append("id = ").append(taskEntry.getKey()).append(" --> ").append(taskEntry.getValue());
                builder.append("\n");
            }
        }
        System.out.println(builder);

        System.out.print("Введите id задачи, которую хотите редактировать (0 - выход): ");
        int taskID;
        if (scanner.hasNextInt()) {
            taskID = scanner.nextInt();
            if (taskID == 0) {
                System.out.println("\n");
            } else if (map.containsKey(taskID)) {
                System.out.print("Новое название задачи: ");
                String taskNewName = scanner.next();
                if (taskNewName.isBlank()) {
                    System.out.println("Необходимо ввести название задачи!!!");
                    scanner.close();
                }
                map.get(taskID).setHeadline(taskNewName);

                System.out.print("Новое описание задачи: ");
                String taskNewDescription = scanner.next();
                if (taskNewDescription.isBlank()) {
                    System.out.println("Необходимо ввести описание задачи!!!");
                    scanner.close();
                }
                map.get(taskID).setDescription(taskNewDescription);
            } else {
                System.out.println("ID задачи не найдено");
                editTask(scanner, map);
            }
        } else {
            System.out.println("ID задачи указан некорректно");
            scanner.close();
        }
    }

    public static void printSortedByDateTasks(Map<Integer, Task> map) {
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getTaskTime().compareTo(o2.getTaskTime());
            }
        };
        List<Task> list = new ArrayList<>(map.values());
        list.sort(comparator);

        Set<LocalDate> dates = new LinkedHashSet<>();
        for (Task task : list) {
            dates.add(task.getTaskTime().toLocalDate());
        }
        printDelimiter();
        for (LocalDate date : dates) {
            System.out.println(date + ": ");
            for (Task task : list) {
                if (task.getTaskTime().toLocalDate().equals(date)) {
                    System.out.println(task.getHeadline() + ", " + task.getDescription() + ", время " + task.getTaskTime().toLocalTime());
                }
            }
        }
        printDelimiter();
    }

    public static void printDeletedTasks(Map<Integer, Task> map) {
        System.out.println("Все удаленные задачи:");
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Task> taskEntry : map.entrySet()) {
            if (taskEntry.getValue().isDeleted()) {
                builder.append("id = ").append(taskEntry.getKey()).append(" --> ").append(taskEntry.getValue());
                builder.append("\n");
            }
        }
        System.out.println(builder);
    }

    private static void printDelimiter() {
        System.out.println("******************************");
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        4. Редактировать задачу
                        5. Посмотреть задачи по датам
                        6. Посмотреть удаленные задачи
                        0. Выход
                        """
        );
    }
}