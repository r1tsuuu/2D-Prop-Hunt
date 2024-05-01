package game;

import math.Vector2;

import java.awt.Color;

import engine.GameCanvas;
import engine.GameFrame;
import game.Character;

public class SampleScene extends GameCanvas{
    public SampleScene(GameFrame frame) {
        super(frame);
        //TODO Auto-generated constructor stub
    }

    public void ready() {
        add(new TestWall(new Vector2(400, 30), new Vector2(100, 100), Color.RED));
        add(new TestWall(new Vector2(300, 240), new Vector2(100, 100), Color.BLUE));
        add(new Character("Eren", Vector2.ZERO, "assets\\freedom.jpg"));
    }
}
