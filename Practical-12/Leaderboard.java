import java.util.*;
public class Leaderboard 
{
    public static void main(String[] args) 
    {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Riya", 95);
        scores.put("Krisha", 88);
        scores.put("Jiya", 95);
        scores.put("Aarav", 78);
        scores.put("Meera", 88);
        ArrayList<Map.Entry<String, Integer>> list =new ArrayList<>(scores.entrySet());
        list.sort((a, b) -> 
        {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue();
            }
            return a.getKey().compareTo(b.getKey());
        });
        int n = 3;
        System.out.println("Top " + n + " Players:");
        for (int i = 0; i < n && i < list.size(); i++) 
        {
            Map.Entry<String, Integer> player = list.get(i);
            System.out.println(player.getKey() + " : " + player.getValue());
        }
    }
}