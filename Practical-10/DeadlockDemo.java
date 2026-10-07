class DeadlockDemo {
    static Object account1 = new Object();
    static Object account2 = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (account1) {
                System.out.println("Thread 1 locked Account 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                synchronized (account2) {
                    System.out.println("Thread 1 locked Account 2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (account1) {
                System.out.println("Thread 2 locked Account 1");
                synchronized (account2) {
                    System.out.println("Thread 2 locked Account 2");
                }
            }
        });
        t1.start();
        t2.start();
        System.out.println("Both threads use the same lock order.");
        System.out.println("Deadlock is avoided.");
    }
}
