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
            for (PhysicsObject object : physicsObjects) {
                object.physicsProcess((System.nanoTime() - previousTime) / 1_000_000_000f);
            }
        }
    }

    public void add(PhysicsObject obj) {
        physicsObjects.add(obj);
    }
}
