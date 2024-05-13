package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import engine.GameObject;
import engine.drawing.DrawingObject;
import engine.physics.CollisionBox;
import engine.physics.PhysicsObject;
import math.Vector2;

public class Wall extends GameObject implements PhysicsObject {

    private Vector2 position;
    private Vector2 size;

    /**
     * Instantiates a wall given start and end positions that form a rectangle.
     */
    public Wall(Vector2 position, Vector2 endPosition) {
        this.position = position;
        size = Vector2.add(endPosition, Vector2.multiply(position, -1));
        new CollisionBox(this, position, size, 0);
    }

    /**
     * Required function when implementing Physics object
     */
    @Override
    public void physicsProcess(float delta) {
    }

    /**
     * invoked when collided with another PhysicsObject.
     */
    @Override
    public void collided(PhysicsObject other, String direction, float offset) {
    }

}
