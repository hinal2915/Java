import java.util.*;
public class UniqueVisitors
{
    public static void main(String[] args) 
    {
        String[] visitors = {
            "Riya", "Krisha", "Jiya",
            "Riya", "Aarav", "Krisha",
            "Meera", "Jiya"
        };
        HashSet<String> unique = new HashSet<>();
        LinkedHashSet<String> order =new LinkedHashSet<>();
        for (String name : visitors) 
        {
            unique.add(name);
            order.add(name);
        }
        System.out.println("Unique visitors: " + unique.size());
        System.out.println("First three visitors:");
        int count = 0;
        for (String name : order) 
        {
            System.out.println(name);
            count++;
            if (count == 3) 
            {
                break;
            }
        }
    }
}