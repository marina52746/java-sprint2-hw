import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        Logic logic = new Logic();
        logic.monthFill();
        while (command != 0)  {
            if (command == 1) {
                logic.readAllMonthReports();
            } else if (command == 2) {
                logic.readYearReport();
            } else if (command == 3) {
                logic.checkReports();
            } else if (command == 4) {
                logic.printMonthReportsInfo();
            } else if (command == 5) {
                logic.printYearReportInfo();
            } else {
                System.out.println("Такой команды в данной программе не существует");
            }
            printMenu();
            command = scanner.nextInt();
        }
        System.out.println("Завершена работа в программе");
    }

    public static void printMenu() {
        System.out.println("Введите номер пункта меню:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из  программы, завершить работу");
    }
}

