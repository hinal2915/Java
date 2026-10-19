import java.util.HashMap;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Id {
}
class Account 
{
    @Id
    String accountNumber;
    String ownerName;
    long balance;
    Account(String accountNumber, String ownerName, long balance) 
    {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    public String toString() 
    {
        return accountNumber + " | "+ ownerName + " | Balance: " + balance;
    }
}
class Repository<T> 
{
    HashMap<String, T> data = new HashMap<>();
    T save(T entity) 
    {
        try 
        {
            Field[] fields =entity.getClass().getDeclaredFields();
            for (Field field : fields) 
            {
                if (field.isAnnotationPresent(Id.class)) 
                {
                    field.setAccessible(true);
                    String id =String.valueOf(field.get(entity));
                    data.put(id, entity);
                    return entity;
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    T findById(String id) 
    {
        return data.get(id);
    }
    ArrayList<T> findAll() 
    {
        return new ArrayList<>(data.values());
    }
    boolean delete(String id) 
    {
        return data.remove(id) != null;
    }
    @SafeVarargs
    final void saveAll(T... items) 
    {
        for (T item : items) 
        {
            save(item);
        }
    }
    static <N extends Number> double average(N[] values) 
    {
        double sum = 0;
        for (N value : values) 
        {
            sum += value.doubleValue();
        }
        return sum / values.length;
    }
}
public class MiniBank 
{
    public static void main(String[] args) 
    {
        Repository<Account> repository =new Repository<>();
        Account a1 =new Account("AC001", "Riya", 5000);
        Account a2 =new Account("AC002", "Krisha", 8000);
        Account a3 =new Account("AC003", "Jiya", 3000);
        repository.save(a1);
        repository.save(a2);
        repository.save(a3);
        System.out.println("Find AC002:");
        System.out.println(repository.findById("AC002"));
        System.out.println("\nAll Accounts:");
        for (Account account : repository.findAll()) 
        {
            System.out.println(account);
        }
        System.out.println("\nAverage:");
        Integer[] numbers = {10, 20, 30};
        System.out.println(Repository.average(numbers));
        System.out.println("\nDelete AC003:");
        System.out.println(repository.delete("AC003"));
    }
}
