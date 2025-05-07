import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    static  final Lock microondas = new ReentrantLock();
    public static void accederAlRecurso() {
       try {
           while (!microondas.tryLock(500, TimeUnit.MILLISECONDS)) {
               System.out.println("Esperando el microondas...");
           }
       }catch (InterruptedException e) {
           System.out.println("ALARMA DE INCENDIOS");
           return;git
         }
        System.out.println("Calentando la comida en el microondas...");
        try {
            Thread.sleep(2000);
            System.out.println("Comida caliente");
        } catch (InterruptedException e) {
            System.out.println("ALARMA DE INCENDIOS");
            System.exit(1);
        }finally {
            microondas.unlock();
            System.out.println("El microondas se ha apagado");
        }
    }

    public static void main(String[] args) {
        Runnable r=()->accederAlRecurso();
        Thread t1= new Thread(r);
        Thread t2= new Thread(r);
        t1.start();
        t2.start();
    }
}
