import java.util.concurrent.atomic.AtomicInteger;

public class Atomics {
     static AtomicInteger total = new AtomicInteger(0);
    public static class  Counter implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
               total.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Counter());
        Thread t2 = new Thread(new Counter());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total: " + total);
    }


}
