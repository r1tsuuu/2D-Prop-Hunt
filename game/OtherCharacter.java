package game;

import engine.network.NetworkInObject;
import math.Vector2;

public class OtherCharacter extends AnimatedSprite implements NetworkInObject{
    String type;

    public OtherCharacter(String name, Vector2 position, int w, int h, String path, int fps, int xFrameCount) {
        super(path, w, h, fps, position, xFrameCount);
    }

    @Override
    public void receive(String input) {
        if (input.charAt(0) != 'p') return;
        var result = input.split(" ");
        var newPos = new Vector2(Float.parseFloat(result[1]), Float.parseFloat(result[2]));
        getPosition().set(newPos);
    }
    
}
