/**
	The seeker class extends the player class and is instantiated by the
    Seeker Client Point of view. It has a gun to shoot with and a tracker
    device.

    @author Alinus Abuke (230073)	
    @author Neil Aldous Biason (230940)
    @version 13 May 2024

    We have not discussed the Java language code in our program 
    with anyone other than our instructor or the teaching assistants 
    assigned to this course.

    We have not used Java language code obtained from another student, 
    or any other unauthorized source, either modified or unmodified.

    If any Java language code or documentation used in our program 
    was obtained from another source, such as a textbook or website, 
    that has been clearly noted with a proper citation in the comments 
    of our program.
**/

package game;

import java.io.DataOutputStream;
import java.io.IOException;

import engine.input.Input.GameInput;
import math.Vector2;

public class Seeker extends Player {

    float gunAngle;
    Vector2 mouseFramePosition;

    /**
     * Instantiates the Seeker in the given position.
     * 
     * @param position
     */
    public Seeker(Vector2 position) {
        super("Seeker", 24, 24, 12, position, "assets\\professor_walk_cycle_no_hat.png", 9);
        gunAngle = 0;
        mouseFramePosition = Vector2.ZERO;
    }

    /**
     * Receives mouse motion input to aim as well as keyboard controls
     * 
     * @param input.
     */
    @Override
    public void inputEvent(GameInput input) {
        super.inputEvent(input);
        if (input instanceof GameInput.MouseMotion mouseMotion) {
            mouseFramePosition = mouseMotion.getPosition();
        }

    }

    /**
     * returns the angle of the gun.
     * 
     * @return angle in raidans.
     */
    public float getGunAngle() {
        return gunAngle;
    }

    /**
     * Chooses the correct animation to draw with the x value being the current
     * frame while the
     * y value the direction the seeker is facing
     * 
     * @param delta time since last frame.
     */
    @Override
    public void process(float delta) {
        if (!velocity.equals(Vector2.ZERO))
            super.process(delta);

        Vector2 topLeft = Vector2.multiply(camera.getCenter(), -1);
        Vector2 globalMousePosition = Vector2.add(topLeft, Vector2.multiply(mouseFramePosition, 1 / camera.getScale()));
        Vector2 localMouse = Vector2.add(globalMousePosition,
                Vector2.multiply(Vector2.add(getPosition(), Vector2.multiply(getSize(), 0.5f)), -1));

        gunAngle = localMouse.getAngle();

        if (up)
            y = 0;
        if (down)
            y = 2;
        if (left)
            y = 1;
        if (right)
            y = 3;

    }

    /**
     * Sends the current position, the current animation frame, the direction, and the angle of the gun
     * to the server
     */
    @Override
    public void send(DataOutputStream dataOut) {
        try {
            dataOut.writeUTF("s " + getPosition().toString() + " " + x + " " + y + " " + gunAngle);
        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }
}
