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

    protected String file;

    public Sprite(String file, int w, int h, int x, int y, Vector2 position) {
        super(position);
        this.file = file;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        loadSprite();
    }

    public void loadSprite() {
        try {
            System.out.println("Loading: " + file + "...");
            SPRITESHEET = ImageIO.read(new File(file));
            wSprite = SPRITESHEET.getWidth() / w;
            hSprite = SPRITESHEET.getHeight() / h;
            loadSpriteArray();
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int w) {
        this.w = w;
        wSprite = SPRITESHEET.getWidth() / w;
        loadSpriteArray();
    }

    public void setHeight(int h) {
        this.h = h;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }

    public void setFile(String spriteFile) {
        this.file = spriteFile;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    private void loadSpriteArray() {
        spriteArray = new BufferedImage[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }

    }

    public BufferedImage getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public Vector2 getSize() {
        return new Vector2(w, h);
    }

    @Override
    public void process(float delta) {
        // dgds
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(spriteArray[y][x], (int) getPosition().getX(), (int) getPosition().getY(), null);
    }
}
