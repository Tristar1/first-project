
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager {
    HashMap<String,MonthlyReport> monthlyReports;
    HashMap<String,YearlyReport> yearlyReports;
    HashMap<String,String> monthNames;

    public void getMonthlyReports() {

        for (int i = 1; i<4; i++) {
            String fileName = "m.20210" + i + ".csv";
            String fileContents = readFileContentsOrNull("D:/reports/" + fileName);
            if (fileContents != null) {

                String[] lines = fileContents.split(System.lineSeparator());
                MonthlyReport monthlyData = new MonthlyReport();

                for (int k = 1; k < lines.length; k++) {
                    String[] lineContents = lines[k].split(",");

                    monthlyData.addExpense(lineContents);
                }
                monthlyData.refreshStatistic();
                monthlyReports.put("20210"+i,monthlyData);
            }
        }

        if (monthlyReports.isEmpty()) {
            System.out.println("Не удалось получить данные месячных отчетов!");
        }
        else System.out.println("Данные месячных отчетов успешно загружены!");

    }

    public void getYearlyReport() {

        //for (int i = 1; i<4; i++) {
            String fileName = "y.2021.csv";
            String fileContents = readFileContentsOrNull("D:/reports/" + fileName);
            if (fileContents != null) {

                String[] lines = fileContents.split(System.lineSeparator());
                YearlyReport yearlyReport = new YearlyReport();

                for (int k = 1; k < lines.length; k++) {
                    String[] lineContents = lines[k].split(",");
                    yearlyReport.addExpense(lineContents);

                }
                yearlyReport.refreshStatistic();
                yearlyReports.put("2021",yearlyReport);
            }

        if (yearlyReports.isEmpty()) {
            System.out.println("Не удалось получить данные годового отчета!");
        }
        else System.out.println("Данные годового отчета успешно загружены!");

    }

    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public void printMonthlyStatistic() {
        if (monthlyReports.isEmpty()) {
            System.out.println("Инфрмация о месячных очтетах не загружена!");
        }
        else{
            for (String monthlyReport : monthlyReports.keySet()) {
                System.out.println(monthNames.get(monthlyReport.substring(monthlyReport.length()-2)));
                MonthlyReport monthlyData = new MonthlyReport();
                monthlyData = monthlyReports.get(monthlyReport);
                System.out.println("Максимальная выручка была по товару: " + monthlyData.maxProfit_item + " " + " на сумму " + monthlyData.maxProfit);
                System.out.println("Максимальная трата была по товару: " + monthlyData.maxExpense_item + " " + " на сумму " + monthlyData.maxExpense);
            }
        }
    }

    public void printYearlyStatistic() {
        if (yearlyReports.isEmpty()) {
            System.out.println("Инфрмация о годовых очтетах не загружена!");
        }
        else{
            for (String yearlyReport : yearlyReports.keySet()) {
                System.out.println(yearlyReport);
                YearlyReport yearlyData = new YearlyReport();
                yearlyData = yearlyReports.get(yearlyReport);
                for (String monthlyGain : yearlyData.gainData.keySet()) {
                    System.out.println("В месяце " + monthNames.get(monthlyGain) + " прибыль составила " + yearlyData.gainData.get(monthlyGain));
                }
                System.out.println("Среднемесячные расходы составили " + yearlyData.averageExpense);
                System.out.println("Среднемесячные доходы составили " + yearlyData.averageProfit);
            }
        }
    }

    public void checkData() {
        boolean mistake = false;
        if (yearlyReports.isEmpty()) {
            System.out.println("Инфрмация о годовых очтетах не загружена!");
            mistake = true;
        }
        if (monthlyReports.isEmpty()) {
            System.out.println("Инфрмация о месячных отчетах не загружена!");
            mistake = true;
        }

        if (mistake) return;

        mistake = false;

        for (String yearlyReportKey : yearlyReports.keySet()) {
            YearlyReport yearlyReport = yearlyReports.get(yearlyReportKey);
            for (int i = 1 ; i<=12 ; i++) {
                String monthKey = Integer.toString(i);
                monthKey = monthKey.length() == 1 ? "0" + monthKey : monthKey;

                MonthlyReport monthlyReport =  monthlyReports.get(yearlyReportKey + monthKey);

                //проверяем что данные за этот месяц есть в каждом виде отчетов
                if (monthlyReport == null && yearlyReport.gainData.get(monthKey) == null) continue;

                if (monthlyReport.totalProfit != yearlyReport.profitData.get(monthKey)) {
                    mistake = true;
                    System.out.println("Сумма доходов за " + monthNames.get(monthKey) + " расходится в годовом и месяыном отчетах");
                }

                if (monthlyReport.totalExpense != yearlyReport.expenseData.get(monthKey)) {
                    mistake = true;
                    System.out.println("Сумма расходов за " + monthNames.get(monthKey) + " расходится в годовом и месяыном отчетах");
                }

                if (!mistake) System.out.println("Расхождений в данных отчетов не обнаружено!");

            }
        }

    }

    private HashMap monthNames() {
        HashMap<String,String> monthNames = new HashMap<>();
        monthNames.put("01","Январь");
        monthNames.put("02","Февраль");
        monthNames.put("03","Март");
        monthNames.put("04","Апрель");
        monthNames.put("05","Май");
        monthNames.put("06","Июнь");
        monthNames.put("07","Июль");
        monthNames.put("08","Август");
        monthNames.put("09","Сентябрь");
        monthNames.put("10","Октябрь");
        monthNames.put("11","Ноябрь");
        monthNames.put("12","Декабрь");

        return monthNames;
    }

    public AccountManager(){
        monthlyReports = new HashMap<>();
        yearlyReports = new HashMap<>();
        monthNames = new HashMap<>();
        monthNames = monthNames();
    }


}