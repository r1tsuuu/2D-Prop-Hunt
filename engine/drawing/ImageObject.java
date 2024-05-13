/**
	The ImageObject class provides basic image functionality that quickly allows
    images to be imported in the game. It extends the GameObject class, inheriting
    positional data and implements DrawingObject class to draw images on the frame.

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

package engine.drawing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import engine.GameObject;
import math.Vector2;

public class ImageObject extends GameObject implements DrawingObject {
    private BufferedImage image;
    private String path;
    private float angle;
    private Vector2 offset;

    /**
     * Creates a new ImageObject with name from position and loads the image from
     * path.
     * 
     * @param name     name of ImageObject (used for debugging)
     * @param position positional data of ImageObject
     * @param path     of image to draw in the canvas.
     */
    public ImageObject(String name, Vector2 position, String path) {
        super(name, position);
        this.path = path;
        angle = 0;
        offset = new Vector2(Vector2.ZERO);
        image = loadImage();
    }

    /**
     * Loads the given image from the path.
     * 
     * @return the buffered image.
     */
    private BufferedImage loadImage() {
        System.out.println("attempting loading " + getName());
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File(path));
            System.out.println("loaded " + path);
        } catch (Exception e) {
            System.out.println("Loading Image Error!");
        }
        return temp;
    }

    /**
     * Repositions the drawn image by offset amount.
     * 
     * @param offset the offset.
     */
    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    /**
     * Draws the ImageObject in the position + offset using the Graphics2D object.
     * 
     * @param g2d Graphics2D object used to draw the image.
     */
    @Override
    public void draw(Graphics2D g2d) {
        var reset = g2d.getTransform();
        g2d.translate(getPosition().getX() + offset.getX(), getPosition().getY() + offset.getY());
        g2d.rotate(angle, getSize().getX() / 2, getSize().getY() / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.setTransform(reset);
    }

    /**
     * Required function when implementing DrawingObject
     */
    @Override
    public void process(float delta) {
    }

    /**
     * @return size of the image as a vector.
     */
    public Vector2 getSize() {
        return new Vector2(image.getWidth(), image.getHeight());
    }

    /**
     * Rotates the drawn image.
     * @param angle of the image to draw
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }
}
