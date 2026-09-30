class Seats 
{
    int seatsLeft = 5;
    int booked = 0;
    void book() 
    {
        if (seatsLeft > 0) 
        {
            seatsLeft--;
            booked++;
            System.out.println(Thread.currentThread().getName() + " booked");
        }
    }

    synchronized void safeBook() 
    {
        if (seatsLeft > 0) 
        {
            seatsLeft--;
            booked++;
            System.out.println(Thread.currentThread().getName() + " booked");
        }
    }
}
public class seatBooking 
{
    public static void main(String[] args) throws Exception 
    {
        Seats seats = new Seats();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) 
        {
            threads[i] = new Thread(() -> seats.book(),"User-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) 
        {
            t.join();
        }
        System.out.println("Without synchronization:");
        System.out.println("Booked: " + seats.booked);
        System.out.println("Seats left: " + seats.seatsLeft);
        Seats safeSeats = new Seats();
        for (int i = 0; i < 10; i++) 
        {
            threads[i] = new Thread(() -> safeSeats.safeBook(),"SafeUser-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) 
        {
            t.join();
        }
        System.out.println("With synchronization:");
        System.out.println("Booked: " + safeSeats.booked);
        System.out.println("Seats left: " + safeSeats.seatsLeft);
    }
}