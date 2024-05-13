package game;

import engine.network.NetworkInObject;
import math.Vector2;

public class OtherCharacter extends AnimatedSprite implements NetworkInObject{
    String type;

    public OtherCharacter(String name, Vector2 position, int w, int h, String path, int fps, int xFrameCount) {
        super(path, w, h, 0, position, xFrameCount);
    }

    @Override
    public void receive(String input) {
        if (input.charAt(0) == 'p' || input.charAt(0) == 's') {
            var result = input.split(" ");
            var newPos = new Vector2(result[1], result[2]);
            x = Integer.parseInt(result[3]);
            if (input.charAt(0) == 's') {
                y = Integer.parseInt(result[4]);
            }
            getPosition().set(newPos);
        }
    }
    
}
