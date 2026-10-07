import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
        System.out.println(Thread.currentThread().getName()+ " deposited " + amount+ " | Balance: " + balance);
    }
    synchronized void withdraw(long amount) 
    {
        if (balance >= amount) 
        {
            balance = balance - amount;
            System.out.println(Thread.currentThread().getName()+ " withdrew " + amount + " | Balance: " + balance);
        } 
        else 
        {
            System.out.println("Insufficient balance");
        }
    }
}
class TransactionProcessor 
{
    ExecutorService pool;
    TransactionProcessor() 
    {
        pool = Executors.newFixedThreadPool(4);
    }
    void submit(Runnable task) 
    {
        pool.execute(task);
    }
    void stop() throws Exception 
    {
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
public class MiniBank 
{
    public static void main(String[] args) throws Exception 
    {
        Account account =new Account("AC001", "Riya", 10000);
        TransactionProcessor processor =new TransactionProcessor();
        for (int i = 1; i <= 5; i++) 
        {
            processor.submit(() ->account.deposit(1000));
            processor.submit(() ->account.withdraw(500));
        }
        processor.stop();
        System.out.println();
        System.out.println("Final Balance: " + account.balance);
    }
}