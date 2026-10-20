package app;
import model.Account;
import service.BankService;
public class MiniBank 
{
    public static void main(String[] args) 
    {
        try 
        {
            BankService bank = new BankService();
            Account a1 =new Account("AC001", "Riya", 5000);
            Account a2 =new Account("AC002", "Krisha", 8000);
            bank.addAccount(a1);
            bank.addAccount(a2);
            System.out.println("Before saving:");
            bank.listAccounts();
            bank.save("accounts.dat");
            System.out.println("\nData saved successfully.");
            BankService newBank = new BankService();
            newBank.load("accounts.dat");
            System.out.println("\nAfter loading:");
            newBank.listAccounts();
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}