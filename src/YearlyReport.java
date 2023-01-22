import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyReportLine> lines;

    public YearlyReport(int Year) {
        year = Year;
        lines = new ArrayList<>();
    }

    public int year;
    public int sumIncome = 0;
    public int sumExpense = 0;
    public int avgIncome = 0;
    public int avgExpense = 0;

    public void getSumAll() {
        for (YearlyReportLine line : lines) {
            if (line.isExpense) {
                sumExpense += line.amount;
            }
            else {
                sumIncome += line.amount;
            }
        }
    }

    public void averageAmount() {
        if (lines.size() != 0) {
            avgExpense = Math.round(sumExpense / lines.size());
            avgIncome = Math.round(sumIncome / lines.size());
        }
    }

    public void addLine(String[] lineContents) {
        YearlyReportLine line = new YearlyReportLine();
        line.month = Byte.parseByte(lineContents[0]);
        line.amount = Integer.parseInt(lineContents[1]);
        line.isExpense = Boolean.parseBoolean(lineContents[2]);
        lines.add(line);
    }
}

