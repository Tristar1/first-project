import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    ArrayList<YearlyData> expenses;
    HashMap<String,Double> gainData;
    HashMap<String,Double> expenseData;
    HashMap<String,Double> profitData;
    double averageExpense;
    double averageProfit;

    public void addExpense(String[] expenseData) {

        boolean is_Expense = Boolean.parseBoolean(expenseData[2]);
        int monthNumber = Integer.parseInt(expenseData[0]);
        double sum = Double.parseDouble(expenseData[1]);

        YearlyData expense = new YearlyData(is_Expense,monthNumber,sum);

        expenses.add(expense);

        //refreshStatistic();

    }

    public void refreshStatistic() {
        int tmp_monthNumber = 1;
        double gain = 0;
        for (YearlyData expense : expenses) {

            if (tmp_monthNumber == expense.month) {
                gain = gain + (expense.is_expense ? -expense.amount : expense.amount);
            }
            else {
                gain = (expense.is_expense ? -expense.amount : expense.amount);
                tmp_monthNumber = expense.month;
            }

            String monthKey = Integer.toString(expense.month);
            monthKey = monthKey.length() == 1 ? "0" + monthKey : monthKey;
            gainData.put(monthKey,gain);

            averageProfit += expense.is_expense ? 0 : expense.amount;
            averageExpense += !expense.is_expense ? 0 : expense.amount;

            if (expense.is_expense)
                expenseData.put(monthKey,expense.amount);
            else
                profitData.put(monthKey,expense.amount);

        }

        // разделим на количество месяцев, чтобы усреднить
        averageProfit = averageProfit / gainData.size();
        averageExpense = averageExpense / gainData.size();
    }

    public YearlyReport(){
        expenses = new ArrayList<>();
        gainData = new HashMap<>();
        expenseData = new HashMap<>();
        profitData = new HashMap<>();
        averageExpense = 0;
        averageProfit = 0;
    }
}

