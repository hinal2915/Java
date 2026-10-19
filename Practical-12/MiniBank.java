import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
class Account implements Comparable<Account> 
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
    public int compareTo(Account other) 
    {
        return accountNumber.compareTo(other.accountNumber);
    }
    public String toString() 
    {
        return accountNumber + " | "+ ownerName + " | Balance: " + balance;
    }
}
class AccountStore 
{
    HashMap<String, Account> accounts =new HashMap<>();
    ArrayList<String> history =new ArrayList<>();
    void add(Account account) 
    {
        accounts.put(account.accountNumber, account);
        history.add("Added " + account.accountNumber);
    }
    Account get(String accountNumber) 
    {
        return accounts.get(accountNumber);
    }
    ArrayList<Account> getAll() 
    {
        return new ArrayList<>(accounts.values());
    }
    ArrayList<Account> sortedByBalance() 
    {
        ArrayList<Account> list =new ArrayList<>(accounts.values());
        list.sort((a, b) -> Long.compare(b.balance, a.balance));
        return list;
    }
}
class TransactionProcessor 
{
    ConcurrentHashMap<String, Account> accounts =new ConcurrentHashMap<>();
    void add(Account account) 
    {
        accounts.put(account.accountNumber, account);
    }
    Account get(String accountNumber) 
    {
        return accounts.get(accountNumber);
    }
}
public class MiniBank 
{
    public static void main(String[] args) 
    {
        AccountStore store = new AccountStore();
        store.add(new Account("AC001", "Riya", 5000));
        store.add(new Account("AC002", "Krisha", 8000));
        store.add(new Account("AC003", "Jiya", 3000));
        store.add(new Account("AC004", "Meera", 10000));
        System.out.println("Accounts sorted by balance:");
        ArrayList<Account> sorted =store.sortedByBalance();
        for (Account account : sorted) 
        {
            System.out.println(account);
        }
        System.out.println("\nSearch Account:");
        Account a = store.get("AC002");
        System.out.println(a);
        System.out.println("\nTransaction History:");
        for (String h : store.history) 
        {
            System.out.println(h);
        }
        TransactionProcessor processor =new TransactionProcessor();
        for (Account account : store.getAll()) 
        {
            processor.add(account);
        }
        System.out.println("\nThread-safe account count: "+ processor.accounts.size());
    }
}