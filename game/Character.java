package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.drawing.ImageObject;
import engine.input.Input.GameInput;
import engine.input.InputObject;
import engine.physics.PhysicsObject;
import math.Vector2;

public class Character extends ImageObject implements PhysicsObject, InputObject {

    boolean up, down, left, right = false;

    public Character() {
        super("Eren Yeager", Vector2.ZERO, "assets\\freedom.jpg");
    }

    @Override
    public void physicsProcess(float delta) {
        if (up) {
            getPosition().add(Vector2.multiply(Vector2.UP, 100 * delta));
        }
        
    }

    @Override
    public void checkCollision(PhysicsObject other) {
    }

    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Key inputKey) {
            if (inputKey.getKey() == 'w') {
                up = inputKey.getType() == GameInput.Key.PRESSED;
            }
        }
    }
    
}
