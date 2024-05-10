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
    private boolean notifyRemoveBullet;
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
            if (mouseInput.getType() == GameInput.Mouse.PRESSED) {
                if (shot)
                    return;
                bullet = new Bullet(new Vector2(getPosition()), seeker.getGunAngle(), 16 * 9, other);
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
            // System.out.println(t);
            // System.out.println(t);
        }
        if (t > 0.2f) {
            shot = false;
            canvas.remove(bullet);
            notifyRemoveBullet = true;
            t = 0;
        }
    }

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
