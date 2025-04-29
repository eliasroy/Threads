public class Synchronized {

    /**
     * Este ejemplo muestra como se puede corromper la memoria al no usar
     * sincronizacion en un objeto compartido entre varios hilos.
     *
     * @param args
     * @throws InterruptedException
     */
    public static class Contador{
        private int valor;

        /**
         * Este metodo incrementa el valor del contador de forma sincronizada, acceso solo a un hilo
         */
        public synchronized void pint(){
            int old = this.valor;
            old++;
            this.valor = old;
        }
        public int getValor(){
            return this.valor;
        }

    }

    /**
     * Esta clase es un Runnable que se encarga de llamar al metodo pint del
     * contador.
     */
    public static class Contable implements Runnable{
        private CorrupcionMemoria.Contador contador;
        public  Contable(CorrupcionMemoria.Contador contador){
            this.contador = contador;
        }
        @Override
        public void run() {
            contador.pint();
        }
    }

    public static  int HILOS = 1000;

    /**
     * Este metodo crea 1000 hilos que incrementan el valor de un contador
     * compartido. Al final se imprime el valor del contador.
     *
     * @throws InterruptedException
     */
    public static void iterar () throws InterruptedException {
        CorrupcionMemoria.Contador c=new CorrupcionMemoria.Contador();
        Thread hilos[] = new Thread[HILOS];
        for (int i = 0; i < HILOS; i++) {
            hilos[i] = new Thread(new CorrupcionMemoria.Contable(c));
        }

        for (int i = 0; i < HILOS; i++) {
            hilos[i].start();
        }
        for (int i = 0; i < HILOS; i++) {
            hilos[i].join();
        }

        int total = c.getValor();
        if (total != HILOS)
            System.out.println("El total es: " + total);

    }

    public static void main(String[] args) {

        try {
            for (;;){
                iterar();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
