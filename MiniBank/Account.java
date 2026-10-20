import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public synchronized void deposit(long amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount");
        }

        balance += amount;
    }

    public synchronized void withdraw(long amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance");
        }

        balance -= amount;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName +
               " | Balance: " + balance;
    }
}