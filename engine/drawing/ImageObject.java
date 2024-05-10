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
    public ImageObject(String name, Vector2 position, String path) {
        super(name, position);
        this.path = path;
        angle = 0;
        image = loadImage();
    }
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
    @Override
    public void draw(Graphics2D g2d) {
        var reset = g2d.getTransform();
        g2d.translate(getPosition().getX(), getPosition().getY());
        g2d.rotate(angle, getSize().getX() / 2, getSize().getY() / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.setTransform(reset);
    }
    @Override
    public void process(float delta) {
    }
    public Vector2 getSize() {
        return new Vector2(image.getWidth(), image.getHeight());
    }
    protected void setAngle(float angle) {
        this.angle = angle;
    }
}
