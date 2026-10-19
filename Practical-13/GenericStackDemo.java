class MyStack<T> 
{
    T[] items;
    int top = -1;
    MyStack(int size) 
    {
        items = (T[]) new Object[size];
    }
    void push(T value) 
    {
        if (top == items.length - 1) 
        {
            System.out.println("Stack is full");
            return;
        }
        items[++top] = value;
    }
    T pop() 
    {
        if (top == -1) 
        {
            System.out.println("Stack is empty");
            return null;
        }
        return items[top--];
    }
    boolean isEmpty() 
    {
        return top == -1;
    }
}
public class GenericStackDemo 
{
    public static void main(String[] args) 
    {
        MyStack<Integer> numbers = new MyStack<>(5);
        numbers.push(10);
        numbers.push(20);
        numbers.push(30);
        System.out.println(numbers.pop());
        System.out.println(numbers.pop());
        MyStack<String> names = new MyStack<>(5);
        names.push("Riya");
        names.push("Krisha");
        System.out.println(names.pop());
        System.out.println(names.pop());
        System.out.println("Empty: " + names.isEmpty());
        System.out.println(names.pop());
    }
}