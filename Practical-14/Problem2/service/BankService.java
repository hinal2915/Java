package service;
import model.Account;
import java.io.*;
import java.util.ArrayList;
public class BankService 
{
    private ArrayList<Account> accounts = new ArrayList<>();
    public void addAccount(Account account) 
    {
        accounts.add(account);
    }
    public int getAccountCount() 
    {
        return accounts.size();
    }
    public ArrayList<Account> getAccounts() 
    {
        return accounts;
    }
    public void save(String fileName) throws IOException 
    {
        ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(accounts);
        out.close();
    }
    @SuppressWarnings("unchecked")
    public void load(String fileName)throws IOException, ClassNotFoundException 
    {
        ObjectInputStream in =new ObjectInputStream(new FileInputStream(fileName));
        accounts = (ArrayList<Account>) in.readObject();
        in.close();
    }
    public void listAccounts() 
    {
        for (Account account : accounts) 
            {
            System.out.println(account);
        }
    }
}