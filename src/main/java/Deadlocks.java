import java.util.Random;

public class Deadlocks {

    static class Tenedor {

    }
    static class Filosofo implements Runnable{

        private static Random r=new Random();
        private final Tenedor izquierdo, derecho;
        String nombre;

        public Filosofo(String nombre, Tenedor izquierdo, Tenedor derecho) {
            this.izquierdo = izquierdo;
            this.derecho = derecho;
            this.nombre = nombre;
        }
        @Override
        public void run() {
            while (true) {
                pensar();
                comer();
            }
        }
        private void pensar() {
            System.out.println(nombre + " esta pensando");
            esperar(r.nextInt(2000,3000));
        }

        private void esperar(int i) {
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void comer() {
            System.out.println(nombre + " quiere comer");
            esperar(r.nextInt(400,600));
            synchronized (izquierdo) {
                System.out.println(nombre + " ha cogido el tenedor izquierdo");
                esperar(r.nextInt(400,600));
                synchronized (derecho) {
                    System.out.println(nombre + " ha cogido el tenedor derecho");
                    esperar(r.nextInt(400,600));
                    System.out.println(nombre + " esta comiendo");
                    esperar(r.nextInt(400,600));
                }
                System.out.println(nombre + " ha soltado el tenedor derecho");
            }
        }
    }

    public static void main(String[] args) {
        String[] nombres = {"Aristoteles", "Platon", "Socrates", "Descartes", "Kant"};
        Tenedor tenedores[] = new Tenedor[nombres.length];
        for (int i = 0; i < tenedores.length; i++) {
            tenedores[i] = new Tenedor();
        }
        Filosofo[] filosofos= new Filosofo[nombres.length];
        for (int i = 0; i < filosofos.length; i++) {
            Tenedor izquierdo = tenedores[i%tenedores.length];
            Tenedor derecho = tenedores[(i+1)%tenedores.length];
            filosofos[i] = new Filosofo(nombres[i], izquierdo, derecho);
        }
        for(Filosofo f: filosofos){
            new Thread(f).start();
        }


    }

}
