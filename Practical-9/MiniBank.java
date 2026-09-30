class Account 
{
    String accountNumber;
    String ownerName;
    long balance;
    Account(String accountNumber, String ownerName, long balance) 
    {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    synchronized void deposit(long amount) 
    {
        balance = balance + amount;
    }
    synchronized boolean withdraw(long amount) 
    {
        if (balance >= amount) 
        {
            balance = balance - amount;
            return true;
        }
        return false;
    }
    void showBalance() 
    {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner: " + ownerName);
        System.out.println("Balance: " + balance);
    }
}
class AccountWorker implements Runnable 
{
    Account account;
    int times;
    long amount;
    AccountWorker(Account account, int times, long amount) 
    {
        this.account = account;
        this.times = times;
        this.amount = amount;
    }
    public void run() 
    {
        System.out.println(Thread.currentThread().getName() + " started");
        for (int i = 0; i < times; i++) 
        {
            account.deposit(amount);
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
public class MiniBank 
{
    public static void main(String[] args) throws Exception 
    {
        Account account =new Account("AC001", "Riya", 0);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) 
        {
            AccountWorker worker =new AccountWorker(account, 1000, 10);
            threads[i] =new Thread(worker, "Worker-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) 
        {
            t.join();
        }
        System.out.println();
        System.out.println("All threads completed");
        account.showBalance();
    }
}