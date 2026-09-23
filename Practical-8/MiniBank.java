class BankException extends Exception 
{
    BankException(String message) 
    
    {
        super(message);
    }
}
class InsufficientFundsException extends BankException 
{
    long shortfall;
    InsufficientFundsException(long shortfall) 
    {
        super("Insufficient funds");
        this.shortfall = shortfall;
    }
    long getShortfall() 
    {
        return shortfall;
    }
}
class AccountNotFoundException extends BankException 
{
    AccountNotFoundException(String message) 
    {
        super(message);
    }
}
class InvalidAmountException extends BankException 
{
    InvalidAmountException(String message) 
    {
        super(message);
    }
}
class Account 
{
    String accountNumber;
    String ownerName;
    long balance;
    Account(String accountNumber,String ownerName,long balance) 
    {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    void deposit(long amount)throws InvalidAmountException 
    {
        if (amount <= 0) 
        {
            throw new InvalidAmountException("Deposit amount must be greater than 0");
        }
        balance = balance + amount;
        System.out.println("Deposited: " + amount);
    }
    void withdraw(long amount)throws InsufficientFundsException,InvalidAmountException 
    {
        if (amount <= 0) 
        {
            throw new InvalidAmountException("Withdrawal amount must be greater than 0");
        }
        if (amount > balance) 
        {
            long shortfall = amount - balance;
            throw new InsufficientFundsException(shortfall);
        }
        balance = balance - amount;
        System.out.println("Withdrawn: " + amount);
    }
    void transfer(Account to, long amount)throws BankException 
    {
        try 
        {
            if (to == null) 
            {
                throw new AccountNotFoundException("Destination account not found");
            }
            withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer successful");
        }
        catch (BankException e) 
        {
            System.out.println("Transfer failed: "+ e.getMessage());
            throw e;
        }
        finally 
        {
            System.out.println("Transfer operation completed");
        }
    }
    void showBalance()
    {
        System.out.println(accountNumber +" Balance: " +balance);
    }
}
class BankResource implements AutoCloseable 
{
    BankResource() 
    {
        System.out.println("Bank resource opened");
    }
    void process() throws Exception 
    {
        System.out.println("Processing bank operation...");
        throw new Exception("Example error inside resource");
    }
    public void close() 
    {
        System.out.println("Bank resource closed");
    }
}
public class MiniBank 
{
    public static void main(String[] args) 
    {
        Account a1 =new Account("AC001", "Riya", 5000);
        Account a2 =new Account("AC002", "Jiya", 3000);
        try 
        {
            a1.deposit(1000);
        }
        catch (InvalidAmountException e) 
        {
            System.out.println("Deposit failed: "+ e.getMessage());
        }
        finally 
        {
            System.out.println("Deposit operation completed");
        }
        try 
        {
            a1.withdraw(10000);
        }
        catch (InsufficientFundsException e) 
        {
            System.out.println("Withdrawal failed: "+ e.getMessage());
            System.out.println("Shortfall: "+ e.getShortfall());
        }
        catch (InvalidAmountException e) 
        {
            System.out.println("Invalid withdrawal: "+ e.getMessage());
        }
        finally 
        {
            System.out.println("Withdrawal operation completed");
        }
        try 
        {
            a1.transfer(a2, 2000);
        }
        catch (BankException e) 
        {
            System.out.println("Bank error: "+ e.getMessage());
        }
        finally 
        {
            System.out.println("Transfer process finished");
        }
        System.out.println();
        a1.showBalance();
        a2.showBalance();
        System.out.println();
        try (BankResource r =new BankResource()) 
        {
            r.process();
        }
        catch (Exception e) 
        {
            System.out.println("Resource error: "+ e.getMessage());
        }
    }
}