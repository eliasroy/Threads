public class WaitNotify {
    static void esperar() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido");
        }
    }
    static class Bandeja{
        String password=null;
        public void setPassword(String password) {
            this.password = password;
        }
        public String getPassword() {
           if(password==null)
               throw new IllegalStateException("No hay password");
            return password;
        }
    }

    static class Entrega implements Runnable{
        Bandeja bandeja;
        public Entrega(Bandeja bandeja) {
            this.bandeja = bandeja;
        }
        @Override
        public void run() {
            bandeja.setPassword("1234");
        }
    }
    static class Recibe implements Runnable{
        Bandeja bandeja;
        public Recibe(Bandeja bandeja) {
            this.bandeja = bandeja;
        }
        @Override
        public void run() {
           String password = bandeja.getPassword();
            System.out.println("La password es: " + password);
        }

    }

    public static void main(String[] args) {
        Bandeja bandeja = new Bandeja();
        Thread entrega = new Thread(new Entrega(bandeja));
        Thread recibe = new Thread(new Recibe(bandeja));
        entrega.start();
        recibe.start();

    }



}
