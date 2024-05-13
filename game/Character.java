package game;

import java.io.DataOutputStream;
import java.io.IOException;

import engine.input.Input.GameInput;
import engine.network.NetworkInObject;
import engine.network.NetworkOutObject;
import engine.input.InputObject;
import engine.physics.CollisionBox;
import engine.physics.PhysicsObject;
import math.Vector2;

public class Character extends AnimatedSprite
        implements PhysicsObject, InputObject, NetworkOutObject, NetworkInObject {

    protected boolean up, down, left, right = false;
    private int speed = 250;
    protected Camera camera;
    protected Vector2 velocity;

    public Character(String name, int w, int h, int fps, Vector2 position, String path, int xFrameCount) {
        super(path, w, h, fps, position, xFrameCount);
        new CollisionBox(this, position, getSize(), 0);
    }

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

    @Override
    public void send(DataOutputStream dataOut) {
        try {
            dataOut.writeUTF("p " +getPosition().toString());
        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }

    @Override
    public void receive(String input) {
        if (!(input.equals("hider") || input.equals("seeker")))
            return;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
