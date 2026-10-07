class Account 
{
    String name;
    Account(String name) 
    {
        this.name = name;
    }
}
public class DeadlockDemo 
{
    public static void main(String[] args) throws Exception 
    {
        Account a = new Account("Account A");
        Account b = new Account("Account B");
        Thread t1 = new Thread(() -> 
        {
            synchronized (a) 
            {
                System.out.println("T1 locked A");
                try 
                {
                    Thread.sleep(100);
                } catch (Exception e) {}
                synchronized (b) 
                {
                    System.out.println("T1 locked B");
                }
            }
        });
        Thread t2 = new Thread(() -> 
        {
            synchronized (b) 
            {
                System.out.println("T2 locked B");
                try 
                {
                    Thread.sleep(100);
                } catch (Exception e) {}
                synchronized (a) 
                {
                    System.out.println("T2 locked A");
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}