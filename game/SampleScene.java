package game;

import math.Vector2;

import java.awt.Color;

import engine.GameCanvas;
import engine.GameFrame;

public class SampleScene extends GameCanvas {

    Character player;
    Camera camera;

    public SampleScene(GameFrame frame) {
        super(frame);
        // TODO Auto-generated constructor stub
    }

    public void ready() {
        player = new Character("Eren", Vector2.ZERO, "assets\\freedom.jpg");
        camera = new Camera(player.getPosition(), this, Vector2.multiply(player.getSize(), -0.5f), 0.5f);
        add(camera);
        add(new TestWall(new Vector2(400, 30), new Vector2(100, 100), Color.RED));
        add(new TestWall(new Vector2(300, 240), new Vector2(300, 10), Color.BLUE));
        add(player);
        
    }
}
