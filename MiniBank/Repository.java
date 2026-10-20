import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
public class Repository 
{
    private ConcurrentHashMap<String, Account> accounts =new ConcurrentHashMap<>();
    public void save(Account account) 
    {
        accounts.put(account.getAccountNumber(), account);
    }
    public Account findById(String accountNumber) 
    {
        return accounts.get(accountNumber);
    }
    public List<Account> findAll() 
    {
        return new ArrayList<>(accounts.values());
    }
    public boolean delete(String accountNumber) 
    {
        return accounts.remove(accountNumber) != null;
    }
    public int size() 
    {
        return accounts.size();
    }
}