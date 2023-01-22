import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MonthlyReportLine> lines;
    public int year;
    public byte month;

    public void calculateAll() {
        maxAmount();
        getSumAll();
        averageAmount();
    }

    public MonthlyReport(int Year, byte Month) {

        lines = new ArrayList<>();
        year = Year;
        month = Month;
    }
    public int maxIncomeSum = 0;
    public String maxIncomeGoods;
    public int maxExpenseSum = 0;
    public String maxExpenseGoods;
    public int sumIncome = 0;
    public int sumExpense = 0;
    public int avgIncome = 0;
    public int avgExpense = 0;

    public void maxAmount() {
        for (MonthlyReportLine line : lines) {
            if (line.isExpense) {
                if (line.cost > maxExpenseSum) {
                    maxExpenseSum = line.cost;
                    maxExpenseGoods = line.itemName;
                }
            }
            else {
                if (line.cost > maxIncomeSum) {
                    maxIncomeSum = line.cost;
                    maxIncomeGoods = line.itemName;
                }
            }
        }
    }

    public void getSumAll() {
        for (MonthlyReportLine line : lines) {
            if (line.isExpense) {
                sumExpense += line.cost;
            }
            else {
                sumIncome += line.cost;
            }
        }
    }

    public void averageAmount() {
        if (lines.size() != 0) {
            avgExpense = sumExpense / lines.size();
            avgIncome = sumIncome / lines.size();
        }
    }

    public void addLine(String[] lineContents) {
        MonthlyReportLine line = new MonthlyReportLine();
        line.itemName = lineContents[0];
        line.isExpense = Boolean.parseBoolean(lineContents[1]) ;
        line.quantity = Integer.parseInt(lineContents[2]) ;
        line.sumOfOne = Integer.parseInt(lineContents[3]);
        line.cost = line.quantity * line.sumOfOne;
        lines.add(line);
    }
}
