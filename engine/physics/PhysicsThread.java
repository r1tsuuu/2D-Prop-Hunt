package engine.physics;

import java.util.ArrayList;

import engine.GameCanvas;
import game.Wall;

public class PhysicsThread extends Thread {

    private boolean running;
    private GameCanvas canvas;

    private static ArrayList<CollisionBox> boxes;

    public PhysicsThread() {
        boxes = new ArrayList<CollisionBox>();
    }

    public PhysicsThread(GameCanvas canvas) {
        this();
        this.canvas = canvas;
    }

    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }

    public void run() {

        if (canvas == null) {
            return;
        }

        running = true;
        long previousTime = System.nanoTime();
        while (running) {
            long currentTime = System.nanoTime();
            var physicsObjects = canvas.getPhysicsObjects();
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
                    if (boxes.get(i).getOwner() instanceof Wall && boxes.get(j).getOwner() instanceof Wall)
                        continue;
                    if (boxes.get(i).isColliding(boxes.get(j))) {
                        boxes.get(i).boxCollided(boxes.get(j));
                        boxes.get(j).boxCollided(boxes.get(i));
                    }
                }
            }

            previousTime = currentTime;
        }
    }

    public static void addCollisionBox(CollisionBox box) {
        if (boxes == null) {
            boxes = new ArrayList<CollisionBox>();
        }
        boxes.add(box);
    }
}
