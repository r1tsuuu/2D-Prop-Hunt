package game;

import java.io.DataOutputStream;
import java.io.IOException;

import engine.input.Input.GameInput;
import math.Vector2;

public class Seeker extends Player {

    float gunAngle;
    Vector2 mouseFramePosition;
    
    public Seeker(Vector2 position) {
        super("Seeker", 24, 24, 12, position, "assets\\professor_walk_cycle_no_hat.png", 9);
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
        if (!velocity.equals(Vector2.ZERO))
            super.process(delta);

        Vector2 topLeft = Vector2.multiply(camera.getCenter(), -1);
        Vector2 globalMousePosition = Vector2.add(topLeft, Vector2.multiply(mouseFramePosition, 1 / camera.getScale()));
        Vector2 localMouse = Vector2.add(globalMousePosition,
                Vector2.multiply(Vector2.add(getPosition(), Vector2.multiply(getSize(), 0.5f)), -1));

        gunAngle = localMouse.getAngle();

        if (up) y = 0;
        if (down) y = 2;
        if (left) y = 1;
        if (right) y = 3;

    }
    @Override
    public void send(DataOutputStream dataOut) {
        try {
            dataOut.writeUTF("s " + getPosition().toString() + " " + x + " " + y + " " + gunAngle);
        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }
}
