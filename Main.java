import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {
            System.out.println("\n====== Expense Tracker ======");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Set Budget");
            System.out.println("5. View Summary");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input!");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter date (dd-mm-yyyy): ");
                    String date = sc.nextLine();

                    manager.addExpense(category, amount, date);
                    break;

                case 2:
                    manager.viewExpenses();
                    break;

                case 3:
                    manager.viewExpenses();
                    System.out.print("Enter index to delete: ");
                    int index = sc.nextInt();
                    manager.deleteExpense(index);
                    break;

                case 4:
                    System.out.print("Enter budget: ");
                    double budget = sc.nextDouble();
                    manager.setBudget(budget);
                    break;

                case 5:
                    manager.viewSummary();
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}