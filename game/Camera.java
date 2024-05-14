/**
	Camera class acts as the 2d camera that is anchored to a position.
    Translates the Graphics2D object passed to it.
    Objects that are drawn before this camera will be unaffected by the translation.

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

    /**
     * Instantiates a new Camera from position
     * 
     * @param position position
     * @param canvas   canvas
     * @param offset   offset
     * @param scale    scale
     */
    public Camera(Vector2 position, GameCanvas canvas, Vector2 offset, float scale) {
        setPosition(position);
        this.canvas = canvas;
        this.offset = offset;
        this.scale = scale;
        center = calculateCenter();
    }

    /**
     * Calculates the new center and linear interpolates to it from the old center
     */
    @Override
    public void process(float delta) {
        var newCenter = calculateCenter();
        center = Vector2.lerp(center, newCenter, 5 * delta);
    }

    /**
     * Translates and scales the Graphics2D objects.
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.scale(scale, scale);
        g2d.translate(center.getX(), center.getY());
    }

    /**
     * Calculates the new center to translate the Graphics 2D by
     * 
     * @return the new center;
     */
    private Vector2 calculateCenter() {
        return Vector2.multiply(getPosition(), -1)
                .add(Vector2.multiply(new Vector2(canvas.getSize()), 0.5f / scale))
                .add(offset);
    }

    /**
     * Returns the current [NOT TARGET] center
     * 
     * @return current center;
     */
    public Vector2 getCenter() {
        return new Vector2(center);
    }

    /**
     * Returns the scale
     * 
     * @return scale
     */
    public float getScale() {
        return scale;
    }
}
