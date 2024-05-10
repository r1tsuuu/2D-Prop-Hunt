package game;

import java.io.DataOutputStream;
import java.io.IOException;

import math.Vector2;

public class Hider extends Character {

    private boolean sendShot;

    public Hider(String name, int w, int h, int fps, Vector2 position, String path, int xFrameCount) {
        super(name, w, h, fps, position, path, xFrameCount);
        sendShot = false;
    }

    public void shot() {
        sendShot = true;
    }

    @Override
    public void send(DataOutputStream dataOut) {
        try {
            dataOut.writeUTF("p " + getPosition().toString());
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
