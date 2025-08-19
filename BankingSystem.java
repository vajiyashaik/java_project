
import java.util.*;

class User {
    String username;
    String password;
    double balance;
    List<String> transactions;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: $" + amount);
            System.out.println(" Deposit successful. Current Balance: $" + balance);
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: $" + amount);
            System.out.println("Withdrawal successful. Current Balance: $" + balance);
        } else {
            System.out.println(" Invalid or insufficient funds.");
        }
    }

    void viewBalance() {
        System.out.println(" Current Balance: $" + balance);
    }

    void viewTransactions() {
        System.out.println(" Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactions) {
                System.out.println(t);
            }
        }
    }
}

public class BankingSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, User> users = new HashMap<>();
    static User currentUser = null;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Banking System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println(" Exiting... Thank you!");
                    break;
                default:
                    System.out.println(" Invalid choice.");
            }
        } while (choice != 3);
    }

    static void register() {
        System.out.print("Enter new username: ");
        String username = sc.nextLine();
        if (users.containsKey(username)) {
            System.out.println(" Username already exists.");
            return;
        }
        System.out.print("Enter new password: ");
        String password = sc.nextLine();
        users.put(username, new User(username, password));
        System.out.println(" Registration successful!");
    }

    static void login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            currentUser = users.get(username);
            System.out.println(" Login successful. Welcome " + username + "!");
            userMenu();
        } else {
            System.out.println(" Invalid username or password.");
        }
    }

    static void userMenu() {
        int choice;
        do {
            System.out.println("\n===== User Menu =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    currentUser.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = sc.nextDouble();
                    currentUser.withdraw(wd);
                    break;
                case 3:
                    currentUser.viewBalance();
                    break;
                case 4:
                    currentUser.viewTransactions();
                    break;
                case 5:
                    System.out.println(" Logged out.");
                    currentUser = null;
                    break;
                default:
                    System.out.println(" Invalid choice.");
            }
        } while (choice != 5);
    }
}
