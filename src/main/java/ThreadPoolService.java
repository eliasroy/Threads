import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolService {
    static class TareaFalsa implements Runnable{
        long amount;
        TimeUnit unit;
        String id;
        public TareaFalsa(long amount, TimeUnit unit, String id) {
            this.amount = amount;
            this.unit = unit;
            this.id = id;
        }


        @Override
        public void run() {
            System.out.println("Tarea " + id + " iniciada");
            try {
                Thread.sleep(Duration.of(amount, unit.toChronoUnit()).toMillis());
                System.out.println("Tarea " + id + " finalizada");
            } catch (InterruptedException e) {
                System.out.println("Tarea " + id + " interrumpida");
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        var servicio = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            servicio.execute(new TareaFalsa(1, TimeUnit.SECONDS, "Tarea " + i));
        }
        servicio.shutdown();

    }
}
