/**
	Gun class represents a hitscan gun.
    It is positioned by the seeker and is always pointing to the angle of the mouse.

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

import engine.GameCanvas;
import engine.drawing.ImageObject;
import engine.input.Input.GameInput;
import engine.network.NetworkOutObject;
import engine.input.InputObject;
import math.Vector2;

public class Gun extends ImageObject implements InputObject, NetworkOutObject {

    private Seeker seeker;
    private Bullet bullet;
    private GameCanvas canvas;
    private boolean shot = false;
    private boolean notifyRemoveBullet;
    private float t = 0;
    private OtherPlayer other;

    /**
     * Instantiates the gun at the position.
     * 
     * @param name
     * @param position
     * @param path     image of gun
     * @param seeker
     * @param canvas   GameCanvas that draws it
     * @param other    the target to receive the bullets.
     */
    public Gun(String name, Vector2 position, String path, Seeker seeker, GameCanvas canvas, OtherPlayer other) {
        super(name, position, path);
        this.seeker = seeker;
        this.canvas = canvas;
        System.out.println(seeker);
        this.other = other;
        shot = false;
        setOffset(Vector2.multiply(seeker.getSize(), 0.20f));
    }

    /**
     * Detects when the mouse is pressed and instantiates the bullet
     */
    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Mouse mouseInput) {
            if (mouseInput.getType() == GameInput.Mouse.PRESSED) {
                if (shot)
                    return;
                bullet = new Bullet(new Vector2(getPosition()).add(Vector2.multiply(seeker.getSize(), 0.5f)),
                        seeker.getGunAngle(), 16 * 9, other);
                canvas.add(bullet);
                shot = true;
                t = 0;
            }
        }
    }

    /**
     * checks if enough time has passed to delete the bullet and spawn another.
     */
    @Override
    public void process(float delta) {
        setAngle(seeker.getGunAngle());
        if (shot) {
            t += delta;
        }
        if (t > 0.2f) {
            shot = false;
            canvas.remove(bullet);
            notifyRemoveBullet = true;
            t = 0;
        }
    }

    /**
     * notifies the server to remove the bullet
     */
    @Override
    public void send(DataOutputStream dataOut) {
        try {
            if (notifyRemoveBullet) {
                dataOut.writeUTF("rbullet");
                notifyRemoveBullet = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
