/**
	The Sprite class provides a more robust implementation of Images. It
    allows the usage of subimages to display subareas of the image.

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
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import math.Vector2;
import engine.GameObject;
import engine.drawing.DrawingObject;

public class Sprite extends GameObject implements DrawingObject {

    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    protected int w, h, x, y;
    private int wSprite, hSprite;
    private Vector2 offset;

    protected String file;

    /**
     * Instantiates a new Sprite Object give the following
     * 
     * @param file     path
     * @param w        width of each subimage
     * @param h        height of each subimage
     * @param x        subimage position from left to right
     * @param y        subimage position from top to bottom
     * @param position the position of the image.
     */
    public Sprite(String file, int w, int h, int x, int y, Vector2 position) {
        super(position);
        offset = Vector2.ZERO;
        this.file = file;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        loadSprite();
    }

    /**
     * Loads the spritesheet and storese.
     */
    public void loadSprite() {
        try {
            SPRITESHEET = ImageIO.read(new File(file));
            wSprite = SPRITESHEET.getWidth() / w;
            hSprite = SPRITESHEET.getHeight() / h;
            loadSpriteArray();
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
    }

    /**
     * Sets the offset of the image drawn.
     */
    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    /**
     * Loads the array that contains each buffered image.
     */
    private void loadSpriteArray() {
        spriteArray = new BufferedImage[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }

    }

    /**
     * returns the subimage given x and y values.
     * 
     * @return BufferedImage subimage
     */
    public BufferedImage getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    /**
     * Returns the size of the sprite
     * 
     * @return Vector2 size;
     */
    public Vector2 getSize() {
        return new Vector2(w, h);
    }

    /**
     * Required function when implementing DrawingObject
     */
    @Override
    public void process(float delta) {
        // STATIC, NOT REQ FOR THIS CLASS
    }

    /**
     * Display the selected sprite on the position with offset.
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (spriteArray == null || SPRITESHEET == null) {
            System.out.println("WARNING: Sprite not loaded yet - file: " + file);
            return;
        }
        g2d.drawImage(spriteArray[y][x], (int) (getPosition().getX() + offset.getX()),
                (int) (getPosition().getY() + offset.getY()), null);
    }
}
