public class BlockSynchronized {
    static class Timbre{
        public void timbre(){
            System.out.println("Ding...");
            try {
                Thread.sleep(2000);
                System.out.println("Dong...");
            } catch (InterruptedException e) {
                System.out.println("ALARMA DE INCENDIOS");
            }
        }
    }
    static class Visitante implements Runnable{
        Timbre t;
        public Visitante(Timbre t){
            this.t = t;
        }
        @Override
        public void run() {
            t.timbre();
        }
    }

    public static void main(String[] args) {

        Timbre t = new Timbre();
        Thread v= new Thread(new Visitante(t));
        Thread v1= new Thread(new Visitante(t));
        Thread v2= new Thread(new Visitante(t));
        v.start();
        v1.start();
        v2.start();
        try {
            v.join();
            v1.join();
            v2.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo no ha terminado");
        }

    }
}
