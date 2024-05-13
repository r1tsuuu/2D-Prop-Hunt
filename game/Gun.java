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

    public Gun(String name, Vector2 position, String path, Seeker seeker, GameCanvas canvas, OtherPlayer other) {
        super(name, position, path);
        this.seeker = seeker;
        this.canvas = canvas;
        System.out.println(seeker);
        this.other = other;
        shot = false;
        setOffset(Vector2.multiply(seeker.getSize(), 0.20f));
    }

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
