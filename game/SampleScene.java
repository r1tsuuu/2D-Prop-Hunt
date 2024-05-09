package game;

import math.Vector2;

import java.awt.Color;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;

public class SampleScene extends GameCanvas {

    Character player;
    Camera camera;
    OtherCharacter other;
    String perspective;

    public SampleScene(GameFrame frame, String perspective) {
        super(frame);
        this.perspective = perspective;
    }

    public void ready() {
        if (perspective.equals("hider")) {
            player = new Hider("Eren", 18, 18, 12, new Vector2(Vector2.ZERO), "assets\\yorkie animation.png", 4);
            other = new OtherCharacter("OtherSeeker", new Vector2(0,0), 24, 24, "assets\\professor_walk_cycle_no_hat.png", 12, 9);

        } else if (perspective.equals("seeker")) {
            player = new Seeker();
            other = new OtherCharacter("impostor", new Vector2(Vector2.ZERO), 18, 18, "assets\\yorkie animation.png", 12, 4);
        }

        camera = new Camera(player.getPosition(), this, Vector2.multiply(player.getSize(), -0.5f), 2f);
        player.setCamera(camera);
        
        add(camera);
        add(new ImageObject("map", Vector2.ZERO, "assets\\map.png"));
        add(new TestWall(new Vector2(400, 30), new Vector2(500, 130), Color.RED));
        add(player);
        add(other);
        if (perspective.equals("seeker")) {
            add(new Gun("Pistol", player.getPosition(), "assets\\gun.png", (Seeker) player, this));
        }
        if (perspective.equals("hider")) {
            add(new ImageObject("OtherPistol", other.getPosition(), "assets\\gun.png"));
        }

        System.out.println("I am " + perspective);
    }

    @Override
    public void networkNotified(String input) {

    }
}
