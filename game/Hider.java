package game;

import java.io.DataOutputStream;
import java.io.IOException;
import engine.input.Input.GameInput;
import math.Vector2;

public class Hider extends Character {
    private boolean sendShot;

    private int propCount;

    public Hider(String name, int w, int h, int fps, Vector2 position, String path, int xFrameCount) {
        super(name, w, h, fps, position, path, xFrameCount);
        sendShot = false;
        propCount = xFrameCount;
    }

    public void changeProp() {
        int prev = x;
        while (x == prev)
            x = (int) (Math.random() * propCount);    
    }

    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Key inputKey) {
            if (inputKey.getKey() == 'w') {
                up = inputKey.getType() == GameInput.Key.TYPED;
            }
            if (inputKey.getKey() == 'a') {
                left = inputKey.getType() == GameInput.Key.TYPED;
            }
            if (inputKey.getKey() == 's') {
                down = inputKey.getType() == GameInput.Key.TYPED;
            }
            if (inputKey.getKey() == 'd') {
                right = inputKey.getType() == GameInput.Key.TYPED;
            }
            if (inputKey.getKey() == 'f' && inputKey.getType() == GameInput.Key.PRESSED) {
                changeProp();
            }
        }
    }

    public void shot() {
        sendShot = true;
    }

    @Override
    public void send(DataOutputStream dataOut) {
        try {
            dataOut.writeUTF("p " + getPosition().toString() + " " + x);
            if (sendShot) {
                dataOut.writeUTF("winSeeker");
                System.out.println("sending my loss");
                sendShot = false;
            }
        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }

}