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
            System.out.println("Esperando a que se entregue la password");
            esperar();
            bandeja.setPassword("1234");
            System.out.println("La password ha sido entregada");
            synchronized (bandeja){
                bandeja.notify();
                System.out.println("Notificando a los que esperan");
            }
        }
    }
    static class Recibe implements Runnable{
        Bandeja bandeja;
        public Recibe(Bandeja bandeja) {
            this.bandeja = bandeja;
        }
        @Override
        public void run() {

          synchronized (bandeja){
              try {
                    System.out.println("Esperando a que se reciba la password");
                    bandeja.wait();
                    System.out.println("La password ha sido recibida");
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
              String password = bandeja.getPassword();
              System.out.println("La password es: " + password);
          }
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
