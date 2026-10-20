package model;
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
    public void deposit(long amount) {
        balance += amount;
    }
    @Override
    public String toString() 
    {
        return accountNumber + " | "+ ownerName + " | Balance: " + balance;
    }
}