class MyResource implements AutoCloseable 
{
    MyResource() 
    {
        System.out.println("Resource opened");
    }
    void work() throws Exception 
    {
        System.out.println("Working...");
        throw new Exception("Error inside resource");
    }
    public void close() 
    {
        System.out.println("Resource closed");
    }
}
public class ResourceDemo 
{
    public static void main(String[] args) 
    {
        try (MyResource r = new MyResource()) 
        {
            r.work();
        }
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}