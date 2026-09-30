class Counter
{
    int count=0;
    void increment()
    {
        count++;
    }
    synchronized void safeIncrement()
    {
        count++;
    }
}
class CounterWorker extends Thread
{
    Counter counter;
    int times;
    boolean safe;
    CounterWorker(Counter counter,int times,boolean safe)
    {
        this.counter=counter;
        this.times=times;
        this.safe=safe;
    }
    public  void run()
    {
        for(int i=0;i<times;i++)
        {
            if(safe)
                counter.safeIncrement();
            else
                counter.increment();
        }
    }
}
public class counterRace 
{
    public static void main(String[] args) throws Exception
    {
        Counter counter=new Counter();
        Thread[] threads=new Thread[10];
        for(int i=0;i<10;i++)
        {
            threads[i]=new CounterWorker(counter, 1000, false);
            threads[i].start();;
        }
        for(Thread t:threads)
        {
            t.join();
        }
        System.out.println("Without syncronization: " + counter.count);
        Counter safeCounter=new Counter();
        for(int i=0;i<10;i++)
        {
            threads[i]=new CounterWorker(safeCounter, 1000, true);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("With synchronization: " + safeCounter.count);
    }    
}
