public class Lock {
    public static void accederAlRecurso() {
        System.out.println("Calentando la comida en el microondas...");
        try {
            Thread.sleep(2000);
            System.out.println("Comida caliente");
        } catch (InterruptedException e) {
            System.out.println("ALARMA DE INCENDIOS");
            System.exit(1);
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
