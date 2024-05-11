package game;

import java.io.DataOutputStream;
import java.io.IOException;

import java.util.ArrayList;

import engine.input.Input.GameInput;
import math.Vector2;

public class Hider extends Character {
    private boolean sendShot;

    private Sprite sprite;
    private Prop currentProp;
    private ArrayList<Prop> availableProps;

    public Hider(String name, int w, int h, int fps, Vector2 position, String path, int xFrameCount) {
        super(name, w, h, fps, position, path, xFrameCount);
        sendShot = false;
        sprite = new AnimatedSprite(currentProp.getFile(), 16, 16, 10, position, 0);

        availableProps = new ArrayList<Prop>();
        availableProps.add(new Prop("assets\\props.png", 16, 16, 0, 0, position, name));
        availableProps.add(new Prop("assets\\props.png", 16, 16, 1, 0, position, name));
        availableProps.add(new Prop("assets\\props.png", 16, 16, 2, 0, position, name));
        availableProps.add(new Prop("assets\\props.png", 16, 16, 3, 0, position, name));
        availableProps.add(new Prop("assets\\props.png", 16, 16, 4, 0, position, name));
        currentProp = availableProps.get(0);
    }

    public void changeProp() {
        if (!availableProps.isEmpty()) {
            int randomIndex = (int) (Math.random() * availableProps.size());
            Prop newProp = availableProps.get(randomIndex);
            currentProp = newProp;

            sprite.setFile(currentProp.getFile());
            sprite.loadSprite();
        }
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
            if (inputKey.getKey() == 'f')
                changeProp();
        }
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
