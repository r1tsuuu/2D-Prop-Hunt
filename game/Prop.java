package game;

import math.Vector2;

public class Prop extends Sprite {
    private String name;

    public Prop(String file, int w, int h, int x, int y, Vector2 position, String name) {
        super(file, w, h, x, y, position);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }
}
