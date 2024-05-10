package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import engine.GameObject;
import engine.drawing.DrawingObject;
import math.Vector2;

public class Bullet extends GameObject implements DrawingObject{

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Bullet(Vector2 start, float angle, float length) {
       var end = new Vector2(angle).multiply(length);
       x1 = (int)start.getX();
       y1 = (int)start.getY();
       x2 = (int)end.getX() + x1;
       y2 = (int)end.getY() + y1;
    }

    @Override
    public void process(float delta) {

    }

    @Override
    public void draw(Graphics2D g2d) {
        
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.YELLOW);
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));

    }
    
}
