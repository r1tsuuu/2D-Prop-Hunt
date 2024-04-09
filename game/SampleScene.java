package game;

import engine.drawing.ImageObject;
import math.Vector2;
import engine.GameCanvas;

public class SampleScene extends GameCanvas{
    public SampleScene() {
        add(new ImageObject("Eren", Vector2.ZERO, "assets\\freedom.jpg"));
    }
}
