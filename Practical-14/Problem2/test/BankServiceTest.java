package test;
import model.Account;
import service.BankService;
public class BankServiceTest 
{
    public static void main(String[] args) 
    {
        try 
        {
            BankService bank = new BankService();
            Account account =new Account("AC001", "Riya", 5000);
            bank.addAccount(account);
            bank.save("test.dat");
            BankService loadedBank =new BankService();
            loadedBank.load("test.dat");
            Account loaded =loadedBank.getAccounts().get(0);
            if (loaded.getAccountNumber().equals(account.getAccountNumber())&& loaded.getOwnerName().equals(account.getOwnerName())&& loaded.getBalance()== account.getBalance()) 
            {
                System.out.println("Save and reload test PASSED");
            } 
            else 
            {
               System.out.println("Save and reload test FAILED");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Test Error: "+ e.getMessage());
        }
    }
}