abstract class Account {
    String accountNumber;
    String ownerName;
    long balance;

    Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    abstract double interestRate();

    abstract boolean canWithdraw(long amount);

    void deposit(long amount) {
        balance = balance + amount;
    }

    boolean withdraw(long amount) {
        if (canWithdraw(amount)) {
            balance = balance - amount;
            return true;
        }
        return false;
    }

    void showDetails() {
        System.out.println("Account: " + accountNumber);
        System.out.println("Owner: " + ownerName);
        System.out.println("Balance: " + balance);
        System.out.println("Interest Rate: " + interestRate() + "%");
    }
}

class SavingsAccount extends Account {
    long minBalance;

    SavingsAccount(String accountNumber, String ownerName,
                   long balance, long minBalance) {
        super(accountNumber, ownerName, balance);
        this.minBalance = minBalance;
    }

    double interestRate() {
        return 4.0;
    }

    boolean canWithdraw(long amount) {
        return balance - amount >= minBalance;
    }
}

class CurrentAccount extends Account {
    long overdraftLimit;

    CurrentAccount(String accountNumber, String ownerName,
                   long balance, long overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    double interestRate() {
        return 0;
    }

    boolean canWithdraw(long amount) {
        return balance - amount >= -overdraftLimit;
    }
}

class FixedDepositAccount extends Account {

    FixedDepositAccount(String accountNumber, String ownerName,
                        long balance) {
        super(accountNumber, ownerName, balance);
    }

    double interestRate() {
        return 7.0;
    }

    boolean canWithdraw(long amount) {
        return false;
    }
}

public class minibank {
    public static void main(String[] args) {

        Account[] accounts = {
            new SavingsAccount("AC001", "Riya", 10000, 5000),
            new CurrentAccount("AC002", "Krisha", 5000, 3000),
            new FixedDepositAccount("AC003", "Jiya", 20000)
        };

        for (Account a : accounts) {

            System.out.println("--------------------");
            a.showDetails();

            System.out.println("Can withdraw 6000: "
                    + a.canWithdraw(6000));
            if (a instanceof FixedDepositAccount) {
                System.out.println("This is a Fixed Deposit Account");
            }
        }
    }
}