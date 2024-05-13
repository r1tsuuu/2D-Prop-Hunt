/**
	TTracker class is a GameObject class that implements Drawing Object
    and Input Object. It creates a tracker that shows the time left it can be used
    as well as the general direction and distance of the hider from the seeker.

    @author Alinus Abuke (230073)	
    @author Neil Aldous Biason (230940)
    @version 13 May 2024

    We have not discussed the Java language code in our program 
    with anyone other than our instructor or the teaching assistants 
    assigned to this course.

    We have not used Java language code obtained from another student, 
    or any other unauthorized source, either modified or unmodified.

    If any Java language code or documentation used in our program 
    was obtained from another source, such as a textbook or website, 
    that has been clearly noted with a proper citation in the comments 
    of our program.
**/

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

    private OtherPlayer other;
    private Seeker seeker;
    private Color color;
    private Vector2 start;
    private Vector2 endPos;
    private boolean visible;
    private float maxLength;
    private float length;
    private float time;
    private float maxTime;
    private float maxDistance;
    private float minDistance;
    private boolean canTrack;

    /**
     * Create the tracker with reference to the seeker and the
     * other player to track position of.
     * 
     * @param seeker seeker
     * @param other  hider
     */
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

    /**
     * Process and update the tracker to point to the hider and display properly.
     * The tracker becomes shorter the longer it is used and it becomes more
     * transparent
     * as it approaches the hider.
     */
    @Override
    public void process(float delta) {
        if (!canTrack)
            return;
        var angle = Vector2.add(other.getPosition(), Vector2.multiply(getPosition(), -1)).getAngle();
        start = Vector2.add(getPosition(), Vector2.multiply(seeker.getSize(), 0.5f));
        endPos = new Vector2(angle).multiply(length)
                .add(getPosition())
                .add(Vector2.multiply(other.getSize(), 0.5f));
        if (visible) {
            length = maxLength - (maxLength * time / maxTime);
            time += delta;
            if (time > maxTime) {
                time = maxTime;
                visible = false;
            }

            float distance = Vector2.getDistance(getPosition(), other.getPosition());
            distance = Math.min(distance, maxDistance - 0.01f);
            float alpha = distance / maxDistance;
            if (distance < minDistance) {
                alpha = 0;
                visible = false;
            }
            color = new Color(1, 0, 0, alpha);
        }
    }

    /**
     * Draws the tracker as a line.
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (!visible)
            return;
        var line = new Line2D.Float(start.getX(), start.getY(), endPos.getX(), endPos.getY());
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(color);
        g2d.draw(line);
    }

    /**
     * Whenever f is pressed, the tracker activates.
     */
    @Override
    public void inputEvent(GameInput input) {
        if (input instanceof GameInput.Key inputKey) {
            if (inputKey.getKey() == 'f' && inputKey.getType() == GameInput.Key.PRESSED) {
                visible = !visible;
            }
        }
    }

    /**
     * The tracker can only activate when the time left is 30 seconds as this is
     * called.
     * 
     * @canTrack canTrack flag
     */
    public void setCanTrack(boolean canTrack) {
        this.canTrack = canTrack;
    }

}
