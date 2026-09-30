class SharedTotal 
{
    long total = 0;
    void add(int value) 
    {
        total = total + value;
    }
    synchronized void safeAdd(int value) 
    {
        total = total + value;
    }
}
class SumWorker extends Thread 
{
    int[] numbers;
    int start;
    int end;
    SharedTotal total;
    boolean safe;
    SumWorker(int[] numbers, int start, int end,SharedTotal total, boolean safe) 
    {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.total = total;
        this.safe = safe;
    }
    public void run() 
    {
        for (int i = start; i < end; i++) 
        {
            if (safe)
                total.safeAdd(numbers[i]);
            else
                total.add(numbers[i]);
        }
    }
}
class LocalSumWorker extends Thread 
{
    int[] numbers;
    int start;
    int end;
    long total = 0;
    LocalSumWorker(int[] numbers, int start, int end) 
    {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    public void run() 
    {
        for (int i = start; i < end; i++) 
        {
            total = total + numbers[i];
        }
    }
}

public class ArraySum 
{
    public static void main(String[] args) throws Exception 
    {
        int[] numbers = new int[10000];
        for (int i = 0; i < numbers.length; i++) 
        {
            numbers[i] = i + 1;
        }
        SharedTotal wrongTotal = new SharedTotal();
        Thread t1 = new SumWorker(numbers, 0, 2500, wrongTotal, false);
        Thread t2 = new SumWorker(numbers, 2500, 5000, wrongTotal, false);
        Thread t3 = new SumWorker(numbers, 5000, 7500, wrongTotal, false);
        Thread t4 = new SumWorker(numbers, 7500, 10000, wrongTotal, false);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Without synchronization: " + wrongTotal.total);
        SharedTotal safeTotal = new SharedTotal();
        t1 = new SumWorker(numbers, 0, 2500, safeTotal, true);
        t2 = new SumWorker(numbers, 2500, 5000, safeTotal, true);
        t3 = new SumWorker(numbers, 5000, 7500, safeTotal, true);
        t4 = new SumWorker(numbers, 7500, 10000, safeTotal, true);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("With synchronization: " + safeTotal.total);
        LocalSumWorker w1 =new LocalSumWorker(numbers, 0, 2500);
        LocalSumWorker w2 =new LocalSumWorker(numbers, 2500, 5000);
        LocalSumWorker w3 =new LocalSumWorker(numbers, 5000, 7500);
        LocalSumWorker w4 =new LocalSumWorker(numbers, 7500, 10000);
        w1.start();
        w2.start();
        w3.start();
        w4.start();
        w1.join();
        w2.join();
        w3.join();
        w4.join();
        long finalTotal =w1.total + w2.total + w3.total + w4.total;
        System.out.println("Using local totals: " + finalTotal);
    }
}