

public class ExpenseData {
    String item_name;
    boolean is_expense;
    int quantity;
    double sum_of_one;
    double total;

    public ExpenseData(String name, boolean expense, int quant, double sum){
        item_name = name;
        is_expense = expense;
        quantity = quant;
        sum_of_one = sum;
        total = quantity * sum_of_one;
    }
}
