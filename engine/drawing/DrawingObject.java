package engine.drawing;

import java.awt.Graphics2D;

public interface DrawingObject{

    public void process(float delta);

    public void draw(Graphics2D g2d);
}
