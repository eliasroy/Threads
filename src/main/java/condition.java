import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class condition {
    static Lock puerta = new ReentrantLock();
    static Condition timbre = puerta.newCondition();
    static volatile String paquet=null;

    static void  sonar(){
      puerta.lock();
      try{
            System.out.println("Ding");
            Thread.sleep(2000);
            paquet="PCGAMER";
            System.out.println("Dong");
            timbre.signalAll();
      }catch (InterruptedException e){
          System.err.println("ALARMA DE INCENDIOS");
      }finally {
          puerta.unlock();
      }

    }
    static void abrirPuerta(){
        puerta.lock();
       try{
           timbre.awaitUninterruptibly();
           System.out.println("Abro la puerta");
           if (paquet==null){
               System.out.println("Mi paquete no ha llegado");
           }else{
               System.out.println("Es justos mi"+paquet);
           }
       }finally {
           puerta.unlock();
       }


    }

    public static void main(String[] args) {
        Thread t1 = new Thread(()->abrirPuerta());
        Thread t2 = new Thread(()->sonar());
        t1.start();
        t2.start();


    }

}

