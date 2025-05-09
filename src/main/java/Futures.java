import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Futures {

    public static class Proceso implements Callable<Integer>{
        String name;
        static int next;
        public Proceso(String name) {
            this.name = name;
        }
        @Override
        public Integer call(){
            try {
                System.out.println(name + " iniciando");
                Thread.sleep(3000);
                System.out.println(name + " finalizando");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return next++;
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        var future1=  executor.submit(new Proceso("Proceso 1"));
        var future2= executor.submit(new Proceso("Proceso 2"));
        System.out.println("Esperando resultados");
        System.out.println("Resultado 2: " + future2.get());
        System.out.println("Resultado 1: " + future1.get());

        executor.shutdown();
    }
}
