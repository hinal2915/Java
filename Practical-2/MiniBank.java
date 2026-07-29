class Customer 
{
    private String name;
    private String email;
    private String mobile;
    private final String customerId;

    private static long customerCounter = 100;

    private static String generateCustomerId() 
    {
        customerCounter++;
        return "CUST" + customerCounter;
    }

    public Customer(String name, String email, String mobile) 
    {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
    }

    public String getName() 
    {
        return name;
    }

    public String getEmail() 
    {
        return email;
    }

    public String getMobile() 
    {
        return mobile;
    }

    public String getCustomerId() 
    {
        return customerId;
    }
}

class Account 
{

    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountCounter = 0;

    private static String generateAccountNumber() 
    {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public Account(String ownerName, long openingBalance) 
    {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) 
    {
        this(ownerName, 0);
    }

    public void deposit(long amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) 
    {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }

    public String getOwnerName() 
    {
        return ownerName;
    }

    public long getBalance() 
    {
        return balance;
    }

    public boolean isActive() 
    {
        return active;
    }
}

public class MiniBank 
{
    public static void main(String[] args) 
    {
        Customer c1 = new Customer("Riya", "riya@gmail.com", "987654****");
        Customer c2 = new Customer("Priya", "priya@gmail.com", "912345****");
        Customer c3 = new Customer("Amit", "amit@gmail.com", "998877****");
        Account[] accounts = new Account[3];
        accounts[0] = new Account(c1.getName(), 5000);
        accounts[1] = new Account(c2.getName());
        accounts[2] = new Account(c3.getName(), 10000);
        accounts[0].deposit(2000);
        accounts[0].withdraw(1500);

        accounts[1].deposit(3000);
        accounts[1].withdraw(500);

        accounts[2].deposit(5000);
        boolean success = accounts[2].withdraw(20000);

        if (!success) 
        {
            System.out.println("Withdrawal failed for " + accounts[2].getOwnerName());
        }
        System.out.println("\n----- Account Details -----");

        for (Account acc : accounts) 
        {
            System.out.println("Account Number : " + acc.getAccountNumber());
            System.out.println("Owner          : " + acc.getOwnerName());
            System.out.println("Balance        : ₹" + acc.getBalance());
            System.out.println("Active         : " + acc.isActive());
        }
    }
}