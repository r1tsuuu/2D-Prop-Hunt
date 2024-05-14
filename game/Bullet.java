/**
	The Bullet class represents the bullet shot by the Gun.
    It is a hitscan that checks whether it intersects the target hider and
    displays a line.

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
import java.io.DataOutputStream;
import java.io.IOException;

import engine.GameObject;
import engine.drawing.DrawingObject;
import engine.network.NetworkOutObject;
import math.Vector2;

public class Bullet extends GameObject implements DrawingObject, NetworkOutObject {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private Vector2 start;
    private Vector2 end;
    private boolean shot;
    private float angle;
    private boolean dataSent;
    private OtherPlayer other;

    /**
     * Instantiates a bullet. Uses trigonometry to figure out the end position of
     * the line.
     * 
     * @param start  position
     * @param angle  in radians
     * @param length float
     * @param other  the target of the bullet
     */
    public Bullet(Vector2 start, float angle, float length, OtherPlayer other) {
        this.angle = angle;
        this.start = start;
        this.other = other;
        end = new Vector2(angle).multiply(length).add(start);
        x1 = (int) start.getX();
        y1 = (int) start.getY();
        x2 = (int) end.getX();
        y2 = (int) end.getY();
        if (other == null)
            return;

        shot = Vector2.lineIntersectsRectangle(start, end, other.getPosition(), other.getSize());
        dataSent = false;
    }

    /**
     * Draws a line from the bullet fields and properties from start to end.
     */
    @Override
    public void draw(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.YELLOW);
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));

    }

    /**
     * Sends the bullet properties to the server for the other client to display
     */
    @Override
    public void send(DataOutputStream dataOut) {
        try {
            if (dataSent || other == null)
                return;
            dataOut.writeUTF(String.format("b %s %.9f %s", start, angle, shot));
            dataSent = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Required function when implementing DrawingObject
     */
    @Override
    public void process(float delta) {
    }

}
