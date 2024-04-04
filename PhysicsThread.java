import java.util.ArrayList;
public class PhysicsThread extends Thread {
    
    private boolean running;
    private ArrayList physicsObjects;
    
    public void run() {
        running = true;
        long previousTime = System.nanoTime();
        while (running) {
            
        }
    }
}
