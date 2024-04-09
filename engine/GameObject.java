package engine;
import math.Vector2;

public class GameObject {
    
    private String name;
    private Vector2 position;
    
    public GameObject() {
        this("", Vector2.ZERO);
    }
    public GameObject(String name) {
        this(name, Vector2.ZERO);
    }
    public GameObject(Vector2 position) {
        this("", position);
    }
    public GameObject(String name, Vector2 position) {
        this.name = name;
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
