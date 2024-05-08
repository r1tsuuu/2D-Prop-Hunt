package game;

import engine.input.Input.GameInput;
import math.Vector2;

public class Seeker extends Character {

    public Seeker(String name, Vector2 position, String path) {
        super(name, position, path);
    }

    @Override
    public void inputEvent(GameInput input) {
        super.inputEvent(input);
        if (input instanceof GameInput.MouseMotion mouseMotion) {
            //global mouse position = center + size/2 + mouseposition
            Vector2 topLeft = Vector2.multiply(camera.getCenter(), -1);
            Vector2 globalMousePosition = Vector2.add(topLeft, mouseMotion.getPosition().multiply(1/camera.getScale()));
            //Vector2 shiftingCenter = Vector2.add(topLeft, camera.getSize().multiply(0.5f/camera.getScale()));
            Vector2 localMouse = Vector2.add(globalMousePosition, Vector2.multiply(Vector2.add(getPosition(), Vector2.multiply(getSize(), 0.5f)), -1));
    
            System.out.println(localMouse);
        }
    }

}
