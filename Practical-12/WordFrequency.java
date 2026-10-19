import java.util.*;
public class WordFrequency 
{
    public static void main(String[] args) 
    {
        String text = "java is easy and java is powerful and java is popular";
        String[] words = text.toLowerCase().split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) 
        {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        ArrayList<Map.Entry<String, Integer>> list =new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> entry : list) 
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}