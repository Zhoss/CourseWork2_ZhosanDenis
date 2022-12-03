import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, DiaryTask> diary = new HashMap<>();
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

    private static void inputTask(Scanner scanner, Map<Integer, DiaryTask> map) {
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

        DiaryTask diaryTask = new DiaryTask(taskName, taskDescription, taskType, taskYear, taskMonth, taskDay, taskHour, taskMinute, taskRepeatability);
        map.put(diaryTask.getId(), diaryTask);
    }

    public static void deleteTask(Scanner scanner, Map<Integer, DiaryTask> map) {
        System.out.println("Все существующие задачи:");
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, DiaryTask> taskEntry : map.entrySet()) {
            builder.append("id = ").append(taskEntry.getKey()).append(" --> ").append(taskEntry.getValue());
            builder.append("\n");
        }
        System.out.println(builder);

        System.out.print("Введите id задачи, которую хотите удалить (0 - выход): ");
        int taskID;
        if (scanner.hasNextInt()) {
            taskID = scanner.nextInt();
            if (taskID == 0) {
                System.out.println("\n");
            } else if (map.containsKey(taskID)) {
                map.remove(taskID);
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

    public static void findTask(Scanner scanner, Map<Integer, DiaryTask> map) {
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
        for (DiaryTask diaryTask : map.values()) {
            if (diaryTask.getTaskTime().toLocalDate().equals(LocalDate.of(taskYear, taskMonth, taskDay))) {
                System.out.println(diaryTask.getHeadline() + ", " + diaryTask.getDescription());
                taskCounter++;
            }
        }
        System.out.println("Количество найденных задач: " + taskCounter);
        printDelimiter();
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
                        0. Выход
                        """
        );
    }
}