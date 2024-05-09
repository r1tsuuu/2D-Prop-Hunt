package game;


import engine.drawing.ImageObject;
import engine.network.NetworkInObject;
import math.Vector2;

public class OtherCharacter extends ImageObject implements NetworkInObject{

    String type;

    public OtherCharacter(String name, Vector2 position, String path, String type) {
        super(name, position, path);
        this.type = type;
    }

    @Override
    public void receive(String input) {
        var result = input.split(" ");
        var newPos = new Vector2(Float.parseFloat(result[0]), Float.parseFloat(result[1]));
        getPosition().set(newPos);
    }
    
}
