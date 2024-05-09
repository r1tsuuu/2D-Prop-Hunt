package game;

import java.util.ArrayList;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import math.Vector2;

public class Lobby extends GameCanvas {

    public Lobby(GameFrame frame) {
        super(frame);
    }

    @Override
    public void ready() {
        // add(new ImageObject("Ereh", Vector2.ZERO, "assets\\freedom.jpg"));
        add(new Sprite("assets\\professor_walk_cycle_no_hat.png", 64, 64, 1, 1, new Vector2(100, 100)));
        add(new AnimatedSprite("assets\\professor_walk_cycle_no_hat.png", 64, 64, 12, new Vector2(0, 0)));
    }

    @Override
    public void networkNotified(String input) {
        if (input.equals("seeker") || input.equals("hider")) {
            getFrame().startGame(input);
        }
    }

}
