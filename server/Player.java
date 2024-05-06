package server;

import math.Vector2;

public class Player {
    Vector2 position;

    public Player(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
