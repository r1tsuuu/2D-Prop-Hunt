package game;

import engine.GameCanvas;
import engine.drawing.ImageObject;
import engine.input.Input.GameInput;
import engine.input.InputObject;
import math.Vector2;

public class Gun extends ImageObject implements InputObject {

    private Seeker seeker;
    private Bullet bullet;
    private GameCanvas canvas;
    private boolean shot = false;
    private float t = 0;

    public Gun(String name, Vector2 position, String path, Seeker seeker, GameCanvas canvas) {
        super(name, position, path);
        this.seeker = seeker;
        this.canvas = canvas;
        System.out.println(seeker);
    }

    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Mouse mouseInput) {
            if (mouseInput.getType() == GameInput.Mouse.CLICKED) {
                if (shot) return;
                bullet = new Bullet(new Vector2(getPosition()), seeker.getGunAngle(), 16*9);
                canvas.add(bullet);
                shot = true;
                t = 0;
                System.out.println("shot");
            }
        }
    }
    @Override
    public void process(float delta) {
        setAngle(seeker.getGunAngle());
        if (shot) {
            t += delta;
            //System.out.println(t);
        }
        if (t > 0.2f) {
            shot = false;
            canvas.remove(bullet);
            t = 0;
            System.out.println("whoosh");
        }
    }
    
}
