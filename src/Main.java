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
//        scanner.useDelimiter("\n");

        System.out.print("Введите название задачи: ");
        String taskName = null;
        if (!scanner.next().isEmpty() && !scanner.next().isBlank()) {
            taskName = scanner.next();
        } else {
            System.out.println("Необходимо ввести название задачи");
        }

        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.next();

        System.out.print("Введите тип задачи (личная/рабочая): ");
        String taskType = scanner.next();

        System.out.print("Введите дату выполнения (год): ");
        int taskYear = 0;
        try {
            taskYear = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Дата (год) должна быть числом");
//            scanner.reset();
        }

        System.out.print("Введите дату выполнения (месяц): ");
        int taskMonth = 0;
        try {
            taskMonth = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Дата (месяц) должна быть числом");
//            scanner.reset();
        }

        System.out.print("Введите дату выполнения (день месяца): ");
        int taskDay = 0;
        try {
            taskDay = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Дата (день месяца) должна быть числом");
//            scanner.reset();
        }

        System.out.print("Введите время выполнения (час): ");
        int taskHour = 0;
        try {
            taskHour = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Время (час) должно быть числом");
//            scanner.reset();
        }

        System.out.print("Введите время выполнения (минуты): ");
        int taskMinute = 0;
        try {
            taskMinute = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Время (минуты) должно быть числом");
//            scanner.reset();
        }

        System.out.print("Введите повторяемость задачи (однократно/каждый день/каждую неделю/каждый месяц/каждый год): ");
        String taskRepeatability = scanner.next();

        DiaryTask diaryTask = new DiaryTask(taskName, taskDescription, taskType, taskYear, taskMonth, taskDay, taskHour, taskMinute, taskRepeatability);
        map.put(diaryTask.getId(), diaryTask);
    }

    public static void deleteTask(Scanner scanner, Map<Integer, DiaryTask> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, DiaryTask> taskEntry : map.entrySet()) {
            builder.append("id = ").append(taskEntry.getKey()).append(" --> ").append(taskEntry.getValue());
            builder.append("\n");
        }
        System.out.println(builder);

        System.out.print("Введите id задачи, которую хотите удалить (0 - выход): ");
        int taskID = 0;
        try {
            taskID = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ID должен быть числом");
            scanner.reset();
        }
        if (map.containsKey(taskID)) {
            map.remove(taskID);
        } else if (taskID == 0) {
            System.out.println("\n");
        } else {
            System.out.println("Укажите верный ID задачи");
            scanner.reset();
        }
    }

    public static void findTask(Scanner scanner, Map<Integer, DiaryTask> map) {
        scanner.useDelimiter("\n");

        System.out.print("Введите дату искомой задачи (год): ");
        int taskYear = 0;
        try {
            taskYear = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Дата (год) должна быть числом");
            scanner.reset();
        }

        System.out.print("Введите дату искомой задачи (месяц): ");
        int taskMonth = 0;
        try {
            taskMonth = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Дата (месяц) должна быть числом");
            scanner.reset();
        }

        System.out.print("Введите дату искомой задачи (день месяца): ");
        int taskDay = 0;
        try {
            taskDay = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Дата (день месяца) должна быть числом");
            scanner.reset();
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