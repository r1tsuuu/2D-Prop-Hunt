package engine.physics;
import java.util.ArrayList;

public class PhysicsThread extends Thread {

    private boolean running;
    private static ArrayList<PhysicsObject> physicsObjects;

    public void run() {
        physicsObjects = new ArrayList<PhysicsObject>();
        running = true;
        long previousTime = System.nanoTime();
        while (running) {
            long currentTime = System.nanoTime();
            
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println("Thread Error");
            }

            for (int i = 0; i < physicsObjects.size(); i++) {
                var object = physicsObjects.get(i);
                object.physicsProcess((currentTime - previousTime) / 1_000_000_000f);
            }
            previousTime = currentTime;
        }
    }

    public void add(PhysicsObject obj) {
        physicsObjects.add(obj);
    }
}
