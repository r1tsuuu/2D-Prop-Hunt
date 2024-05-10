package game;

import java.awt.Color;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import math.Vector2;

public class SeekerScene extends GameCanvas {

    Character player;
    Camera camera;
    OtherCharacter other;
    String perspective;
    Gun gun;

    public SeekerScene(GameFrame frame) {
        super(frame);
    }

    @Override
    public void ready() {

        player = new Seeker();
        other = new OtherCharacter("impostor", new Vector2(Vector2.ZERO), 18, 18, "assets\\yorkie animation.png", 12,
                4);
        camera = new Camera(player.getPosition(), this, Vector2.multiply(player.getSize(), -0.5f), 2f);
        player.setCamera(camera);
        gun = new Gun("Pistol", player.getPosition(), "assets\\gun.png", (Seeker) player, this, other);

        add(camera);
        add(new ImageObject("map", Vector2.ZERO, "assets\\map.png"));
        add(new TestWall(new Vector2(400, 30), new Vector2(500, 130)));
        add(player);
        add(other);
        add(gun);

    }

}
