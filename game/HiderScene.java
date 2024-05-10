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
}
