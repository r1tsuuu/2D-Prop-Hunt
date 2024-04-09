package engine;
import javax.swing.JComponent;

import engine.drawing.DrawingObject;
import engine.drawing.ImageObject;
import math.Vector2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameCanvas extends JComponent {
    private ArrayList<DrawingObject> drawingObjects;
    
    public GameCanvas() {
        setPreferredSize(new Dimension(800, 600));
        drawingObjects = new ArrayList<DrawingObject>();
    }

    // called every frame
    public void process(float delta) {
        for (DrawingObject drawingObject : drawingObjects) {
            drawingObject.process(delta);
        }
        repaint();
        revalidate();
    }

    @Override public void paintComponent(Graphics g) {
        var g2d = (Graphics2D) g;
        for(DrawingObject drawingObject : drawingObjects){
            drawingObject.draw(g2d);
        }
    }

    public void add(DrawingObject drawingObject) {
        drawingObjects.add(drawingObject);
    }
}