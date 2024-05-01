package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import engine.GameObject;
import engine.drawing.DrawingObject;
import engine.physics.CollisionBox;
import engine.physics.PhysicsObject;
import math.Vector2;

public class TestWall extends GameObject implements DrawingObject, PhysicsObject{

    private Vector2 position;
    private Vector2 size;
    private CollisionBox collision;
    private Color color;

    public TestWall(Vector2 position, Vector2 size, Color color) {
        this.position = position;
        this.size = size;
        this.color = color;
        collision = new CollisionBox(this, position, size, 0);
    }

    @Override
    public void physicsProcess(float delta) {
    }

    @Override
    public void process(float delta) {
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(position.getX(), position.getY(), size.getX(), size.getY()));
    }

    @Override
    public void collided(PhysicsObject other, String direction, float offset) {
    }
    
}
