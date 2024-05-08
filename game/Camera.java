package game;

import java.awt.Graphics2D;

import engine.GameCanvas;
import engine.GameObject;
import engine.drawing.DrawingObject;
import math.Vector2;

public class Camera extends GameObject implements DrawingObject {

    private GameCanvas canvas;
    private Vector2 offset;
    private Vector2 center;
    private float scale;



    public Camera(Vector2 position, GameCanvas canvas, Vector2 offset, float scale) {
        setPosition(position);
        this.canvas = canvas;
        this.offset = offset;
        this.scale = scale;
        center = calculateCenter();
    }

    @Override
    public void process(float delta) {
        var newCenter = calculateCenter();
        center = Vector2.lerp(center, newCenter, 5 * delta);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.scale(scale, scale);
        g2d.translate(center.getX(), center.getY());
    }

    private Vector2 calculateCenter() {
        return Vector2.multiply(getPosition(), -1)
        .add(Vector2.multiply(new Vector2(canvas.getSize()), 0.5f / scale))
        .add(offset);
    }

    public Vector2 getCenter() {
        return new Vector2(center);
    }

    public Vector2 getSize() {
        return new Vector2(canvas.getSize());
    }

    public Vector2 getOffset() {
        return offset;
    }

    public float getScale() {
        return scale;
    }
}
