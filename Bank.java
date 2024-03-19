import java.util.Scanner;

// BankAccount class representing a user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money into the account
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        } else {
            System.out.println("Invalid amount for deposit.");
            return false;
        }
    }

    // Method to withdraw money from the account
    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal.");
            return false;
        }
    }

    // Method to check the current balance
    public double checkBalance() {
        return this.balance;
    }
}

// ATM class representing an automated teller machine
class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    // Constructor to initialize the ATM with a bank account
    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    // Method to display the options to the user
    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    // Method to run the ATM system
    public void run() {
        while (true) {
            System.out.println("\nWelcome to the ATM!");
            displayOptions();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    withdraw();
                    break;
                case "2":
                    deposit();
                    break;
                case "3":
                    checkBalance();
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle withdrawal
    public void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (bankAccount.withdraw(amount)) {
            System.out.println("Withdrew " + amount + " successfully.");
        }
    }

    // Method to handle deposit
    public void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (bankAccount.deposit(amount)) {
            System.out.println("Deposited " + amount + " successfully.");
        }
    }

    // Method to check balance
    public void checkBalance() {
        double balance = bankAccount.checkBalance();
        System.out.println("Your balance is: " + balance);
    }
}

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);
        atm.run();
        scanner.close();
    }
}
