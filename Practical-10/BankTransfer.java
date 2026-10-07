class BankAccount {

    String accountNumber;
    long balance;

    BankAccount(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}

class Transfer {

    static void transfer(
        BankAccount from,
        BankAccount to,
        long amount
    ) {

        BankAccount first;
        BankAccount second;

        if (from.accountNumber.compareTo(to.accountNumber) < 0) {
            first = from;
            second = to;
        } else {
            first = to;
            second = from;
        }

        synchronized (first) {

            synchronized (second) {

                if (from.balance >= amount) {

                    from.balance -= amount;
                    to.balance += amount;

                    System.out.println(
                        "Transferred " + amount
                        + " from "
                        + from.accountNumber
                        + " to "
                        + to.accountNumber
                    );
                }
            }
        }
    }
}

public class BankTransfer {

    public static void main(String[] args)
            throws Exception {

        BankAccount a =new BankAccount("AC001", 10000);
        BankAccount b =new BankAccount("AC002", 10000);
        Thread t1 = new Thread(() -> 
        {
            Transfer.transfer(a, b, 1000);
        });
        Thread t2 = new Thread(() -> 
        {
            Transfer.transfer(b, a, 500);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("A Balance: " + a.balance);
        System.out.println("B Balance: " + b.balance);
    }
}