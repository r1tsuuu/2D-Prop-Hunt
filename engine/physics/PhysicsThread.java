package engine.physics;
import java.util.ArrayList;

public class PhysicsThread extends Thread {

    private boolean running;
    private static ArrayList<PhysicsObject> physicsObjects;
    private static ArrayList<CollisionBox> boxes;

    public void run() {
        boxes = new ArrayList<CollisionBox>();
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

            for (int i = 0; i < boxes.size(); i++) {
                for (int j = i + 1; j < boxes.size(); j++) {
                    if (boxes.get(i).isColliding(boxes.get(j))) {
                        boxes.get(i).boxCollided(boxes.get(j));
                        boxes.get(j).boxCollided(boxes.get(i));
                    }
                }
            }

            previousTime = currentTime;
        }
    }

    public void add(PhysicsObject obj) {
        physicsObjects.add(obj);
    }
    
    public static void addCollisionBox(CollisionBox box) {
        if (boxes == null) {
            boxes = new ArrayList<CollisionBox>();
        }
        boxes.add(box);
    }
}
