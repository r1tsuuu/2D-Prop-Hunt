package engine.physics;

import math.Vector2;

public class CollisionBox {

    private Vector2 position;
    private Vector2 size;
    private int zLevel;
    private PhysicsObject owner;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public int getzLevel() {
        return zLevel;
    }

    public void setzLevel(int zLevel) {
        this.zLevel = zLevel;
    }

    public CollisionBox(PhysicsObject owner, Vector2 position, Vector2 size, int zLevel) {
        this.owner = owner;
        this.position = position;
        this.size = size;
        this.zLevel = zLevel;
        PhysicsThread.addCollisionBox(this);
    }

    public boolean isColliding(CollisionBox other) {
        return Vector2.intersect(position, getEndBounds(), other.position, other.getEndBounds());
    }

    private Vector2 getEndBounds() {
        return Vector2.add(position, size);
    }

    public void boxCollided(CollisionBox other) {
        float x1 = position.getX();
        float y1 = position.getY();
        float x2 = other.position.getX();
        float y2 = other.position.getY();
        float w1 = size.getX();
        float h1 = size.getY();
        float w2 = other.size.getX();
        float h2 = other.size.getY();
        float x = Math.max(x1, x2);
        float y = Math.max(y1, y2);
        float w = Math.min(x1 + w1, x2 + w2) - x;
        float h = Math.min(y1 + h1, y2 + h2) - y;
        String direction = "";
        float offset;
        if (w > h)
        {
            if (y == y1)
                direction = "top";
            else
                direction = "below";
            offset = h;
        }
        else {
            if (x == x1)
                direction = "left";
            else
                direction = "right";
            offset = w;
        }
        owner.collided(other.owner, direction, offset);
        }

}
