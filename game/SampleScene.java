package game;

import engine.drawing.ImageObject;
import math.Vector2;
import engine.GameCanvas;
import engine.GameFrame;

public class SampleScene extends GameCanvas{
    public SampleScene(GameFrame frame) {
        super(frame);
        add(new ImageObject("Eren", Vector2.ZERO, "assets\\freedom.jpg"));
    }
}
