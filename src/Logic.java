import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Logic {
    public ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
    public YearlyReport yearlyReport;
    public boolean IsLoadedYearlyReport;
    public boolean AreLoadedMonthlyReports;
    public List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
    public void readAllMonthReports() {
        List<String> file = new ArrayList<>();
        for (byte i = 1; i <= 3; i++) {
            String fileName = "m.20210" + i + ".csv";
            file = readFileContents("resources/" + fileName);
            MonthlyReport monthlyReport = new MonthlyReport(2021, i);
            for (int j = 1; j < file.size(); j++) {
                String[] lineContents = file.get(j).split(",");
                monthlyReport.addLine(lineContents);
            }
            monthlyReport.calculateAll();
            monthlyReports.add(monthlyReport);
        }
        if (file.size() != 0) {
            AreLoadedMonthlyReports = true;
            System.out.println("Месячные отчёты загружены.");
        }
    }

    public void readYearReport() {
        yearlyReport = new YearlyReport(2021);
        List<String> file = readFileContents("resources/y.2021.csv");
        for (int j = 1; j < file.size(); j++) {
            String[] lineContents = file.get(j).split(",");
            yearlyReport.addLine(lineContents);
        }
        yearlyReport.getSumAll();
        yearlyReport.averageAmount();
        if (file.size() != 0) {
            IsLoadedYearlyReport = true;
            System.out.println("Годовой отчёт загружен.");
        }
    }

    public void checkReports() {
        boolean checkSuccessfull = true;
        if (AreLoadedMonthlyReports && IsLoadedYearlyReport) {
            for(int i = 0; i < monthlyReports.size(); i++) {
                for (YearlyReportLine line : yearlyReport.lines) {
                    if (line.month - 1 == i) {
                        if (line.isExpense) {
                            if (line.amount != monthlyReports.get(i).sumExpense) {
                                checkSuccessfull = false;
                                System.out.println("В " + line.month + " месяце сумма трат в месячном отчете (" + monthlyReports.get(i).sumExpense + ") не совпала с годовым (" + line.amount + ").");
                            }
                        }
                        else {
                            if (line.amount != monthlyReports.get(i).sumIncome) {
                                checkSuccessfull = false;
                                System.out.println("В " + line.month + " месяце сумма доходов в месячном отчете (" + monthlyReports.get(i).sumIncome + ") не совпала с годовым (" + line.amount + ").");
                            }
                        }
                    }
                }
            }
            if (checkSuccessfull) {
                System.out.println("Проверка прошла успешно");
            }
        }
        else {
            if (!AreLoadedMonthlyReports)
                System.out.println("Не загружены месячные отчёты.");
            if (!IsLoadedYearlyReport)
                System.out.println("Не загружен годовой отчёт.");
        }
    }

    public void printMonthReportsInfo() {
        if (!AreLoadedMonthlyReports) {
            System.out.println("Не загружены месячные отчёты.");
            return;
        }
        for (MonthlyReport report : monthlyReports)
        {
            System.out.println("Месяц " + months.get(report.month - 1));
            System.out.println("Самый прибыльный товар: " + report.maxIncomeGoods + ", стоимость " + report.maxIncomeSum);
            System.out.println("Самая большая трата: " + report.maxExpenseGoods + ", стоимость " + report.maxExpenseSum);
            System.out.println();
        }
    }

    public void printYearReportInfo() {
        if (!IsLoadedYearlyReport) {
            System.out.println("Не загружен годовой отчёт.");
            return;
        }
        System.out.println("Год: " + yearlyReport.year);
        System.out.println("Прибыль по месяцам:");
        for (MonthlyReport report : monthlyReports) {
            System.out.println(months.get(report.month - 1) + " " + (report.sumIncome - report.sumExpense));
        }
        System.out.println("Средний расход " + yearlyReport.avgExpense);
        System.out.println("Средний доход " + yearlyReport.avgIncome);
    }
    public static ArrayList<String> months = new ArrayList<>(12);
    public void monthFill() {
        months.add("январь");
        months.add("февраль");
        months.add("март");
        months.add("апрель");
        months.add("май");
        months.add("июнь");
        months.add("июль");
        months.add("август");
        months.add("сентябрь");
        months.add("октябрь");
        months.add("ноябрь");
        months.add("декабрь");
    }
}
