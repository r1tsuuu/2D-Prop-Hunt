package game;

import java.awt.Color;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import math.Vector2;

public class HiderScene extends GameCanvas {
    Character player;
    Camera camera;
    OtherCharacter other;
    ImageObject gun;
    Bullet bullet;
    

    public HiderScene(GameFrame frame) {
        super(frame);
    }

    public void ready() {
        player = new Hider("Eren", 18, 18, 12, new Vector2(Vector2.ZERO), "assets\\yorkie animation.png", 4);
        other = new OtherCharacter("OtherSeeker", new Vector2(0, 0), 24, 24, "assets\\professor_walk_cycle_no_hat.png",
                12, 9);

        camera = new Camera(player.getPosition(), this, Vector2.multiply(player.getSize(), -0.5f), 2f);
        player.setCamera(camera);
        gun = new ImageObject("OtherPistol", other.getPosition(), "assets\\gun.png");
        
        add(camera);
        add(new ImageObject("map", Vector2.ZERO, "assets\\map.png"));
        add(new TestWall(new Vector2(400, 30), new Vector2(500, 130), Color.RED));
        add(player);
        add(other);
        add(gun);
    }

    @Override
    public void networkNotified(String input) {
        if (input.charAt(0) == 'b') {
            var values = input.split(" ");
            var start = new Vector2(values[1], values[2]);
            var angle = Float.parseFloat(values[3]);
            if (values[4].equals("true")) {
                System.out.println("I have been shot!");
            }
            bullet = new Bullet(start, angle, 16*9, null);
            add(bullet);
        }

        if (input.charAt(0) == 's') {
            var value = input.split(" ");
            var gunAngle = Float.parseFloat(value[5]);
            gun.setAngle(gunAngle);
        }

        if (input.equals("rbullet")) {
            remove(bullet);
        }

    }
}
