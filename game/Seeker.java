package game;

import engine.input.Input.GameInput;
import math.Vector2;

public class Seeker extends Character {

    float gunAngle;
    Vector2 mouseFramePosition;

    public Seeker(String name, int w, int h, int fps, Vector2 position, String path) {
        super(name, w, h, fps, position, path);
        gunAngle = 0;
        mouseFramePosition = Vector2.ZERO;
    }

    @Override
    public void inputEvent(GameInput input) {
        super.inputEvent(input);
        if (input instanceof GameInput.MouseMotion mouseMotion) {

            mouseFramePosition = mouseMotion.getPosition();
        }
    }

    public float getGunAngle() {
        return gunAngle;
    }

    @Override
    public void process(float delta) {
        Vector2 topLeft = Vector2.multiply(camera.getCenter(), -1);
        Vector2 globalMousePosition = Vector2.add(topLeft, Vector2.multiply(mouseFramePosition, 1 / camera.getScale()));
        Vector2 localMouse = Vector2.add(globalMousePosition,
                Vector2.multiply(Vector2.add(getPosition(), Vector2.multiply(getSize(), 0.5f)), -1));

        gunAngle = localMouse.getAngle();
    }
}
