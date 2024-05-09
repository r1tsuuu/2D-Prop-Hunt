package game;

import java.util.ArrayList;
import java.util.Vector;

import math.Vector2;

public class AnimatedSprite extends Sprite {
    int fps;
    float totalTime;
    float currentTime;

    public AnimatedSprite(String file, int w, int h, int fps, Vector2 position) {
        super(file, w, h, 0, 0, position);
        this.fps = fps;
        currentTime = 0;
        totalTime = 0;
    }

    @Override
    public void process(float delta) {
        if (totalTime > 1f / fps) {
            totalTime = 0;
            x += 1;
        }
        if (x >= 8) {
            x = 0;
        }
        totalTime += delta;
    }
}