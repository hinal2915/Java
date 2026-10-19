class Box<T> 
{
    T value;
    void set(T value) 
    {
        this.value = value;
    }
    T get() 
    {
        return value;
    }
}
class Pair<A, B> 
{
    A first;
    B second;
    Pair(A first, B second) 
    {
        this.first = first;
        this.second = second;
    }
    void show() 
    {
        System.out.println(first + " - " + second);
    }
}
public class GenericDemo {
    static <T> T firstElement(T[] array) 
    {
        return array[0];
    }
    public static void main(String[] args) 
    {
        Box<String> box1 = new Box<>();
        box1.set("Hello");
        Box<Integer> box2 = new Box<>();
        box2.set(100);
        System.out.println("String Box: " + box1.get());
        System.out.println("Integer Box: " + box2.get());
        Pair<String, Integer> pair =new Pair<>("Riya", 20);
        pair.show();
        String[] names = {"Riya", "Krisha", "Jiya"};
        System.out.println("First element: " + firstElement(names));
    }
}