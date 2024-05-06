package game;


import engine.drawing.ImageObject;
import engine.network.NetworkInObject;
import math.Vector2;

public class OtherCharacter extends ImageObject implements NetworkInObject{

    public OtherCharacter(String name, Vector2 position, String path) {
        super(name, position, path);
    }

    @Override
    public void receive(String input) {
        if (input.equals("party_complete")) return;
        var result = input.split(" ");
        var newPos = new Vector2(Float.parseFloat(result[0]), Float.parseFloat(result[1]));
        setPosition(newPos);
    }
    
}
