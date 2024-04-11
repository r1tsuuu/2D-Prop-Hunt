package game;

import engine.drawing.ImageObject;
import engine.input.Input.GameInput;
import engine.input.InputObject;
import engine.physics.PhysicsObject;
import math.Vector2;

public class Character extends ImageObject implements PhysicsObject, InputObject {

    boolean up, down, left, right = false;
    int speed = 100;

    public Character(String name, Vector2 position, String path) {
        super(name, position, path);
    }

    @Override
    public void physicsProcess(float delta) {
        System.out.println(delta);
        if (up) {
            getPosition().add(Vector2.multiply(Vector2.UP, speed * delta));
        }
        if (down) {
            getPosition().add(Vector2.multiply(Vector2.DOWN, speed * delta));
        }
        if (left) {
            getPosition().add(Vector2.multiply(Vector2.LEFT, speed * delta));
        }
        if (right) {
            getPosition().add(Vector2.multiply(Vector2.RIGHT, speed * delta));
        }
        
    }

    @Override
    public void checkCollision(PhysicsObject other) {
    }

    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Key inputKey) {
            if (inputKey.getKey() == 'w' && inputKey.getType() == GameInput.Key.PRESSED) {
                up = true;
            }
            if (inputKey.getKey() == 'w' && inputKey.getType() == GameInput.Key.RELEASED) {
                up = false;
            }
            if (inputKey.getKey() == 's' && inputKey.getType() == GameInput.Key.PRESSED) {
                down = true;
            }
            if (inputKey.getKey() == 's' && inputKey.getType() == GameInput.Key.RELEASED) {
                down = false;
            }
            if (inputKey.getKey() == 'a' && inputKey.getType() == GameInput.Key.PRESSED) {
                left = true;
            }
            if (inputKey.getKey() == 'a' && inputKey.getType() == GameInput.Key.RELEASED) {
                left = false;
            }
            if (inputKey.getKey() == 'd' && inputKey.getType() == GameInput.Key.PRESSED) {
                right = true;
            }
            if (inputKey.getKey() == 'd' && inputKey.getType() == GameInput.Key.RELEASED) {
                right = false;
            }

        }
    }
    
}
