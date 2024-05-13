package game;

import math.Vector2;

public class AnimatedSprite extends Sprite {

    int fps;
    float totalTime;
    float currentTime;
    int xFrameCount;

    public AnimatedSprite(String file, int w, int h, int fps, Vector2 position, int xFrameCount) {
        super(file, w, h, 0, 0, position);
        this.fps = fps;
        currentTime = 0;
        totalTime = 0;
        this.xFrameCount = xFrameCount;
    }

    @Override
    public void process(float delta) {
        if (fps == 0)
            return;
        if (totalTime > 1f / fps) {
            totalTime = 0;
            x += 1;
        }
        if (x >= xFrameCount) {
            x = 0;
        }
        totalTime += delta;
    }
}