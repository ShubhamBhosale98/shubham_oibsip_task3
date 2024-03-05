
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private Scanner scanner;
    private User currentUser;
    private List<Transaction> transactionHistory;

    public ATM() {
        scanner = new Scanner(System.in);
        transactionHistory = new ArrayList<>();
    }

    public void start() {
        System.out.println("Welcome to the ATM");
        System.out.print("Enter your User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        // Authenticate user
        if (authenticateUser(userId, pin)) {
            showOptions();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }

    private boolean authenticateUser(String userId, String pin) {
        // Dummy authentication
        if (userId.equals("123") && pin.equals("1234")) {
            currentUser = new User(userId);
            return true;
        } else {
            return false;
        }
    }

    private void showOptions() {
        int choice;
        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
            return;
        }
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private void withdraw() {
        // Logic for withdrawal
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        // Record transaction
        transactionHistory.add(new Transaction(currentUser.getUserId(), "Withdraw", amount));
        System.out.println("Withdrawal of $" + amount + " successful.");
    }

    private void deposit() {
        // Logic for deposit
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        // Record transaction
        transactionHistory.add(new Transaction(currentUser.getUserId(), "Deposit", amount));
        System.out.println("Deposit of $" + amount + " successful.");
    }

    private void transfer() {
        // Logic for transfer
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        // Record transaction
        transactionHistory.add(new Transaction(currentUser.getUserId(), "Transfer to " + recipientId, amount));
        System.out.println("Transfer of $" + amount + " to User ID " + recipientId + " successful.");
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

class Transaction {
    private String userId;
    private String type;
    private double amount;

    public Transaction(String userId, String type, double amount) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User: " + userId + ", Type: " + type + ", Amount: " + amount;
    }
}

class User {
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
