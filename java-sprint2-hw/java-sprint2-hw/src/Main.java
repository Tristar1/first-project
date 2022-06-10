import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager acccountManager = new AccountManager();
        while (true) {
            printMenu();
            int userInput = scanner.nextInt();

            if (userInput != 0) {

                if (userInput == 1 ) {
                    acccountManager.getMonthlyReports();
                }
                else if (userInput == 2 ) {
                    acccountManager.getYearlyReport();
                }

                else if (userInput == 3 ) {
                    acccountManager.checkData();
                }

                else if (userInput == 4 ) {
                    acccountManager.printMonthlyStatistic();
                }

                else if (userInput == 5 ) {
                    acccountManager.printYearlyStatistic();
                }

                //printMenu();
            }
            else {
                System.out.println("Программа завершена!");
                break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
    }
}
