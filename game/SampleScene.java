package game;

import math.Vector2;

import java.awt.Color;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;

public class SampleScene extends GameCanvas{

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
            player = new Hider("Eren", new Vector2(Vector2.ZERO), "assets\\yorkie_walk_right.gif");
        } else if (perspective.equals("seeker")) {
            player = new Seeker("Eren", new Vector2(Vector2.ZERO), "assets\\yorkie_walk_right.gif");
        }
        camera = new Camera(player.getPosition(), this, Vector2.multiply(player.getSize(), -0.5f), 2f);
        player.setCamera(camera);
        other = new OtherCharacter("impostor", new Vector2(Vector2.ZERO), "assets\\yorkie_walk_right.gif", perspective);
        add(camera);
        add(new ImageObject("map", Vector2.ZERO, "assets\\map.png"));
        add(new TestWall(new Vector2(400, 30), new Vector2(100, 100), Color.RED));
        add(new TestWall(new Vector2(300, 240), new Vector2(300, 10), Color.BLUE));
        add(new TestWall(new Vector2(300, 290), new Vector2(300, 10), Color.BLUE));
        add(player);
        add(other);
        if (perspective.equals("seeker")) {
            add(new Gun("Pistol", player.getPosition(), "assets\\gun.png", (Seeker) player));
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
