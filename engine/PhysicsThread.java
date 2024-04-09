package engine;
import java.util.ArrayList;

import engine.physics.PhysicsObject;

public class PhysicsThread extends Thread {

    private boolean running;
    private ArrayList<PhysicsObject> physicsObjects;

    public void run() {
        running = true;
        long previousTime = System.nanoTime();
        while (running) {
            for (PhysicsObject object : physicsObjects) {
                
            }
        }
    }
}
