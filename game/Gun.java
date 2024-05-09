package game;

import engine.drawing.ImageObject;
import engine.input.Input.GameInput;
import engine.input.InputObject;
import math.Vector2;

public class Gun extends ImageObject implements InputObject {

    private Seeker seeker;

    public Gun(String name, Vector2 position, String path, Seeker seeker) {
        super(name, position, path);
        this.seeker = seeker;
        System.out.println(seeker);
    }

    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Mouse mouseInput) {
            if (mouseInput.getType() == GameInput.Mouse.CLICKED) {
                System.out.println("bang!");
            }
        }
    }
    @Override
    public void process(float delta) {
        setAngle(seeker.getGunAngle());
    }
    
}
