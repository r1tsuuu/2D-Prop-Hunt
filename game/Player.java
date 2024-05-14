/**
	Player class provides basic topdown movement functionality.
    It is also animated automatically and requires additional
    implementation to choose which animation to play.

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
import engine.network.NetworkOutObject;
import engine.input.InputObject;
import engine.physics.CollisionBox;
import engine.physics.PhysicsObject;
import math.Vector2;

public class Player extends AnimatedSprite
        implements PhysicsObject, InputObject, NetworkOutObject {

    protected boolean up, down, left, right = false;
    private int speed = 250;
    protected Camera camera;
    protected Vector2 velocity;

    /**
     * Instantiates the player
     * 
     * @param name
     * @param w        width of sprite
     * @param h        height of sprite
     * @param fps      speed of animation
     * @param position position
     * @param path     String path of image
     * @param number   of images horizontally in the spritesheet.
     */
    public Player(String name, int w, int h, int fps, Vector2 position, String path, int xFrameCount) {
        super(path, w, h, fps, position, xFrameCount);
        new CollisionBox(this, position, getSize(), 0);
        velocity = new Vector2(Vector2.ZERO);
    }

    /**
     * The Physics of the Player. Adds values to the position.
     * 
     * @param delta time between last call of physicsProcess
     */
    @Override
    public void physicsProcess(float delta) {
        velocity = new Vector2(Vector2.ZERO);
        if (up) {
            velocity.add(Vector2.multiply(Vector2.UP, speed * delta));
        }
        if (down) {
            velocity.add(Vector2.multiply(Vector2.DOWN, speed * delta));
        }
        if (left) {
            velocity.add(Vector2.multiply(Vector2.LEFT, speed * delta));
        }
        if (right) {
            velocity.add(Vector2.multiply(Vector2.RIGHT, speed * delta));
        }
        getPosition().add(velocity);
    }

    /**
     * detects input from the player.
     */
    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Key inputKey) {
            if (inputKey.getKey() == 'w')
                up = inputKey.getType() == GameInput.Key.TYPED;
            if (inputKey.getKey() == 'a')
                left = inputKey.getType() == GameInput.Key.TYPED;
            if (inputKey.getKey() == 's')
                down = inputKey.getType() == GameInput.Key.TYPED;
            if (inputKey.getKey() == 'd')
                right = inputKey.getType() == GameInput.Key.TYPED;

        }
    }

    /**
     * Wall collision of the player, prevents it from passing through walls.
     */
    @Override
    public void collided(PhysicsObject other, String direction, float offset) {
        if (direction.equals("right"))
            getPosition().add(new Vector2(-offset, 0));
        if (direction.equals("left"))
            getPosition().add(new Vector2(offset, 0));
        if (direction.equals("top"))
            getPosition().add(new Vector2(0, offset));
        if (direction.equals("below"))
            getPosition().add(new Vector2(0, -offset));
    }

    /**
     * Sends the positional data to the server for the other player to receive
     */
    @Override
    public void send(DataOutputStream dataOut) {
        try {
            dataOut.writeUTF("p " + getPosition().toString());
        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }

    /**
     * Sets the camera
     * 
     * @param camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
