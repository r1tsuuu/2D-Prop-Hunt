package game;

import java.awt.Graphics2D;

import engine.GameObject;
import engine.drawing.DrawingObject;
import math.Vector2;

public class Bullet extends GameObject implements DrawingObject{

    private Vector2 start;
    private Vector2 end;

    public Bullet(Vector2 start, Vector2 end) {
        
    }

    @Override
    public void process(float delta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
    
}
