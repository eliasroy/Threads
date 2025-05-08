import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLocks {






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
        ReadWriteLock lock= new ReentrantReadWriteLock();
        public void read() {

           try {
               log("Pido permiso de lectura");
                lock.readLock().lock();
                log("Reading...");
                esperar(rnd.nextInt(1000));
                log("Read complete");
            }finally {
                lock.readLock().unlock();
            }

        }
        public void write() {
           try {
                log("Pido permiso de escritura");
                lock.writeLock().lock();
               log("Writing...");
               esperar(rnd.nextInt(1000));
               log("Write complete");
           }finally {
                lock.writeLock().unlock();
           }
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
        tp.submit(write);
        for (int i = 0; i < 10; i++) {
            tp.submit(read);
        }
        tp.shutdown();
    }
}
