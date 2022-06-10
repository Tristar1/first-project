
import java.util.ArrayList;

public class MonthlyReport {
   ArrayList<ExpenseData> expenses;
   double maxExpense;
   double maxProfit;
   String maxExpense_item;
   String maxProfit_item;
   double totalProfit;
   double totalExpense;

    public MonthlyReport(){
        expenses = new ArrayList<>();
        maxExpense = 0;
        maxProfit = 0;
        maxExpense_item = null;
        maxProfit_item = null;
    }

    public void addExpense(String[] expenseData) {

        String item = expenseData[0];
        boolean exp = Boolean.parseBoolean(expenseData[1]);
        int quant = Integer.parseInt(expenseData[2]);
        double sum = Double.parseDouble(expenseData[3]);

        ExpenseData expense = new ExpenseData(item,exp,quant,sum);

        expenses.add(expense);

        //refreshMaxData();
    }

    public void refreshStatistic() {
        for (ExpenseData saleInformation : expenses) {

                if (saleInformation.is_expense) {
                    if (saleInformation.total > maxExpense) {
                        maxExpense = saleInformation.total;
                        maxExpense_item = saleInformation.item_name;
                    }

                    totalExpense += saleInformation.total;
                }

                else
                    if  (saleInformation.total > maxProfit) {
                        maxProfit = saleInformation.total;
                        maxProfit_item = saleInformation.item_name;
                    }

                    totalProfit += saleInformation.total;
        }
    }
}


