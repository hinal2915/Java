import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
class TransactionProcessor 
{
    ExecutorService pool;
    TransactionProcessor() 
    {
        pool = Executors.newFixedThreadPool(4);
    }
    void submit(Runnable task) 
    {
        pool.execute(task);
    }
    void stop() throws Exception 
    {
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}