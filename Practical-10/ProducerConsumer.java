class Buffer 
{
    int[] data = new int[5];
    int count = 0;
    int putIndex = 0;
    int getIndex = 0;
    synchronized void produce(int value) throws Exception 
    {
        while (count == data.length) 
        {
            wait();
        }
        data[putIndex] = value;
        putIndex = (putIndex + 1) % data.length;
        count++;
        System.out.println("Produced: " + value);
        notify();
    }
    synchronized int consume() throws Exception 
    {
        while (count == 0) 
        {
            wait();
        }
        int value = data[getIndex];
        getIndex = (getIndex + 1) % data.length;
        count--;
        System.out.println("Consumed: " + value);
        notify();
        return value;
    }
}
public class ProducerConsumer 
{
    public static void main(String[] args) throws Exception 
    {
        Buffer buffer = new Buffer();
        Thread producer = new Thread(() -> 
        {
            try 
            {
                for (int i = 1; i <= 10; i++) 
                {
                    buffer.produce(i);
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
                    buffer.consume();
                }
            } catch (Exception e) 
            {
                System.out.println(e);
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Production and consumption completed");
    }
}