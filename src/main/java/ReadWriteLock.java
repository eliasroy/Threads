import java.util.Random;
import java.util.concurrent.Executors;

public class ReadWriteLock {






    public static class repositorio{

        static  void  log(String msg){
            String  threadName = Thread.currentThread().getName();
            System.out.println(threadName + " " + msg);
        }
        static void esperar(long ms){
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Random rnd = new Random();

        public void read() {

           log("Reading...");
            esperar(rnd.nextInt(1000));
            log("Read complete");
        }
        public void write() {
           log("Writing...");
            esperar(rnd.nextInt(1000));
            log("Write complete");
        }
    }

    public static void main(String[] args) {
        var repo = new repositorio();
        var tp =Executors.newFixedThreadPool(4);
        Runnable read=()-> repo.read();
        Runnable write=()-> repo.write();

        for (int i = 0; i < 10; i++) {
            tp.submit(read);
        }
        tp.shutdown();
    }
}
