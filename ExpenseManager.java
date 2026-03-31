import java.io.*;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses;
    private double budget;
    private final String FILE_NAME = "data/expenses.dat";

    public ExpenseManager() {
        expenses = new ArrayList<>();
        budget = 0;
        loadExpenses();
    }

    public void addExpense(String category, double amount, String date) {
        Expense expense = new Expense(category, amount, date);
        expenses.add(expense);
        saveExpenses();
        System.out.println("✅ Expense added successfully!");

        if (budget > 0 && getTotalExpenses() > budget) {
            System.out.println("⚠ WARNING: You have exceeded your budget!");
        }
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            saveExpenses();
            System.out.println("🗑 Expense deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n--- All Expenses ---");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(i + ". " + expenses.get(i));
        }
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    public void viewSummary() {
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Expense e : expenses) {
            categoryTotals.put(
                e.getCategory(),
                categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );
        }

        System.out.println("\n--- Expense Summary ---");
        for (String category : categoryTotals.keySet()) {
            System.out.println(category + ": ₹" + categoryTotals.get(category));
        }

        System.out.println("Total Spending: ₹" + getTotalExpenses());
    }

    public void setBudget(double budget) {
        this.budget = budget;
        System.out.println("✅ Budget set to ₹" + budget);
    }

    private void saveExpenses() {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdir();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(expenses);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }
    @SuppressWarnings("unchecked")
    private void loadExpenses() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            expenses = (List<Expense>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            expenses = new ArrayList<>();
        }
    }
}
