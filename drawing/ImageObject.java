package drawing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import math.Vector2;

public class ImageObject extends DrawingObject {
    private BufferedImage image;
    private String path;
    public ImageObject(String name, Vector2 position, String path) {
        super(name, position);
        this.path = path;
        image = loadImage();
    }
    private BufferedImage loadImage() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("Loading Image Error!");
        }
        return temp;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, 0, 0, null)
    }
}
