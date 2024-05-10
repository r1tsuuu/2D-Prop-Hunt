package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.DataOutputStream;
import java.io.IOException;

import engine.GameObject;
import engine.drawing.DrawingObject;
import engine.network.NetworkOutObject;
import math.Vector2;

public class Bullet extends GameObject implements DrawingObject, NetworkOutObject{

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private Vector2 start;
    private Vector2 end;
    private boolean shot;

    private boolean dataSent;

    public Bullet(Vector2 start, float angle, float length, OtherCharacter other) {
        this.start = start;
        end = new Vector2(angle).multiply(length).add(start);
        x1 = (int) start.getX();
        y1 = (int) start.getY();
        x2 = (int) end.getX();
        y2 = (int) end.getY();
        if (other == null) return;
        
        shot = Vector2.lineIntersectsRectangle(start, end, other.getPosition(), other.getSize());
        System.out.println("the boi got shot: " + shot);
        System.out.println(start + "\t" + end);
        System.out.println(other.getPosition() + "\t" + other.getSize());
        dataSent = false;
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

    @Override
    public void send(DataOutputStream dataOut) {
        try {
            if (dataSent) return;
            dataOut.writeUTF(String.format("b %s %s %s", start, end, shot));
            dataSent = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
