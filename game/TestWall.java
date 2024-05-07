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
    private Color color;

    public TestWall(Vector2 position, Vector2 size, Color color) {
        this.position = position;
        this.size = size;
        this.color = color;
        new CollisionBox(this, position, size, 0);
    }

    @Override
    public void physicsProcess(float delta) {
    }

    @Override
    public void process(float delta) {
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        var reset = g2d.getTransform();
        g2d.setColor(color);
        g2d.translate(position.getX(), position.getY());
        g2d.fill(new Rectangle2D.Double(0, 0, size.getX(), size.getY()));
        g2d.setTransform(reset);
    }

    @Override
    public void collided(PhysicsObject other, String direction, float offset) {
    }
    
}
