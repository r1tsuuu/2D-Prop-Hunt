package game;

import math.Vector2;
import engine.GameCanvas;
import engine.GameFrame;
import game.Character;

public class SampleScene extends GameCanvas{
    public SampleScene(GameFrame frame) {
        super(frame);
        //TODO Auto-generated constructor stub
    }

    public void ready() {
        add(new Character("Eren", Vector2.ZERO, "assets\\freedom.jpg"));
    }
}
