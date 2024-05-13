package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import engine.GameObject;
import engine.drawing.DrawingObject;
import engine.input.Input.GameInput;
import engine.input.InputObject;
import math.Vector2;

public class Tracker extends GameObject implements DrawingObject, InputObject {

    OtherPlayer other;
    Seeker seeker;
    Color color;
    Vector2 start;
    Vector2 endPos;
    boolean visible;
    float maxLength;
    float length;
    float time;
    float maxTime;
    float maxDistance;
    float minDistance;
    boolean canTrack;
    

    public Tracker(Seeker seeker, OtherPlayer other) {
        setPosition(seeker.getPosition());
        this.other = other;
        this.seeker = seeker;
        visible = false;
        start = getPosition();
        endPos = getPosition();
        maxLength = 5 * 16;
        length = maxLength;
        maxTime = 30;
        time = 0;
        maxDistance = 50 * 16;
        minDistance = 16 * 16;
        canTrack = false;
    }

    @Override
    public void process(float delta) {
        if (!canTrack)
            return;
        var angle = Vector2.add(other.getPosition(), Vector2.multiply(getPosition(), -1)).getAngle();
        start = Vector2.add(getPosition(), Vector2.multiply(seeker.getSize(), 0.5f));
        endPos = new Vector2(angle).multiply(length)
            .add(getPosition())
            .add(Vector2.multiply(other.getSize(), 0.5f));
        if (visible)
        {
            length = maxLength - (maxLength * time / maxTime);
            time += delta;
            if (time > maxTime) {
                time = maxTime;
                visible = false;
            }

            float distance = Vector2.getDistance(getPosition(), other.getPosition());
            distance = Math.min(distance, maxDistance - 0.01f);
            float alpha = distance / maxDistance;
            if (distance < minDistance)
            {
                alpha = 0;
                visible = false;
            }
            color = new Color(1, 0, 0, alpha);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (!visible)
            return;
        var line = new Line2D.Float(start.getX(), start.getY(), endPos.getX(), endPos.getY());
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(color);
        g2d.draw(line);
    }

    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Key inputKey) {
            if (inputKey.getKey() == 'f' && inputKey.getType() == GameInput.Key.PRESSED) {
                visible = !visible;
            }
        }
    }

    public void setCanTrack(boolean canTrack) {
        this.canTrack = canTrack;
    }

}
