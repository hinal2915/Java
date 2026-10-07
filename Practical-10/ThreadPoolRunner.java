import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class ThreadPoolRunner 
{
    public static void main(String[] args) throws Exception 
    {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) 
        {
            int id = i;
            pool.execute(() -> 
            {
                System.out.println("Task " + id + " running by "+ Thread.currentThread().getName());
                try 
                {
                    Thread.sleep(500);
                } 
                catch (InterruptedException e) 
                {
                    System.out.println("Task interrupted");
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("All tasks completed");
    }
}