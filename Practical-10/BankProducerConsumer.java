class TransactionBuffer 
{
    String[] transactions = new String[5];
    int count = 0;
    int putIndex = 0;
    int getIndex = 0;
    synchronized void add(String transaction) throws Exception 
    {
        while (count == transactions.length) 
        {
            wait();
        }
        transactions[putIndex] = transaction;
        putIndex =(putIndex + 1) % transactions.length;
        count++;
        System.out.println("Added: " + transaction);
        notify();
    }
    synchronized String get() throws Exception 
    {
        while (count == 0) 
        {
            wait();
        }
        String transaction =transactions[getIndex];
        getIndex =(getIndex + 1) % transactions.length;
        count--;
        notify();
        return transaction;
    }
}
public class BankProducerConsumer 
{
    public static void main(String[] args)throws Exception 
    {
        TransactionBuffer buffer =new TransactionBuffer();
        Thread producer = new Thread(() -> 
        {
            try 
            {
                for (int i = 1; i <= 10; i++) 
                {
                    buffer.add("Deposit-" + i);
                }
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
        });
        Thread consumer = new Thread(() -> 
        {
            try 
            {
                for (int i = 1; i <= 10; i++) 
                {
                    String transaction =buffer.get();
                    System.out.println("Processing: " + transaction);
                }
            }
            catch (Exception e) 
            {
                System.out.println(e);
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("All transactions processed");
    }
}