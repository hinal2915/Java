interface Transactable {

    void deposit(long amount);

    boolean withdraw(long amount);
}

interface InterestBearing {

    default double yearlyInterest() {
        return 0;
    }
}

interface WithdrawRule {

    boolean allow(Account account, long amount);
}

interface Premium {
}

abstract class Account implements Transactable, InterestBearing {

    String accountNumber;
    String ownerName;
    long balance;

    Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    abstract double interestRate();

    public void deposit(long amount) {
        balance = balance + amount;
    }

    public boolean withdraw(long amount) {
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        }

        return false;
    }

    public double yearlyInterest() {
        return balance * interestRate() / 100;
    }
}

class SavingsAccount extends Account implements Premium {

    SavingsAccount(String no, String name, long balance) {
        super(no, name, balance);
    }

    double interestRate() {
        return 4.0;
    }
}

class CurrentAccount extends Account {

    CurrentAccount(String no, String name, long balance) {
        super(no, name, balance);
    }

    double interestRate() {
        return 0;
    }
}

class FixedDepositAccount extends Account {

    FixedDepositAccount(String no, String name, long balance) {
        super(no, name, balance);
    }

    double interestRate() {
        return 7.0;
    }

    public boolean withdraw(long amount) {
        return false;
    }
}

public class MiniBank {

    public static void main(String[] args) {

        Account[] accounts = {
            new SavingsAccount("AC001", "Riya", 10000),
            new CurrentAccount("AC002", "Krisha", 15000),
            new FixedDepositAccount("AC003", "Jiya", 20000)
        };

        for (Account a : accounts) {

            System.out.println("Account: " + a.accountNumber);
            System.out.println("Owner: " + a.ownerName);
            System.out.println("Balance: " + a.balance);
            System.out.println("Interest: " +
                    a.yearlyInterest());

            System.out.println();
        }

        WithdrawRule rule1 = new WithdrawRule() {

            public boolean allow(Account account, long amount) {
                return account.balance >= amount;
            }
        };

        System.out.println("Anonymous rule: " +
                rule1.allow(accounts[0], 5000));
                WithdrawRule rule2 =    (account, amount) ->account.balance >= amount;

        System.out.println("Lambda rule: " +
                rule2.allow(accounts[1], 5000));
    }
}