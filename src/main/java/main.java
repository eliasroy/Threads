import java.time.Duration;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread is running");
                Thread.sleep(2000);
                System.out.println("Thread is finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("Main thread is running");
        // Wait for the thread to finish
        //thread.join();
        // If the thread is not finished within 5 seconds, continue
        thread.join(500);

        System.out.println("Main thread is finished");
    }
}
