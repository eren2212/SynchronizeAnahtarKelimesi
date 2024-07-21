
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadSafe {
    private int count;
    
    public synchronized void arttir(){
        count++;
    }
    public void theradleriCalistir(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<5000; i++){
                    arttir();
                }
            }
        });
        
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<5000; i++){
                    arttir();
                }
            }
        });
        
        thread1.start();
        thread2.start();
        
        //burada sout metodu main threadinnde çalıştığı için direkt 0 sayisini
        //basıyor bu yüzden beklemesi için join metodunu kullanıcaz.
        //ama halen senkronizasyon sorunu oluyor onu geçirmemiz içinde anahtar kilit 
        //işini kullanıcaz.
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadSafe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("Count sayisi:"+count);
    }
    
    public static void main(String[] args) {
        ThreadSafe threadSafe = new ThreadSafe();
        
        threadSafe.theradleriCalistir();
    }
           
}
