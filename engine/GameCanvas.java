package engine;

import javax.swing.JComponent;

import engine.drawing.DrawingObject;
import engine.input.Input;
import engine.input.InputObject;
import engine.physics.PhysicsObject;
import engine.physics.PhysicsThread;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameCanvas extends JComponent {

    private ArrayList<DrawingObject> drawingObjects;
    private PhysicsThread physicsThread;
    private Input inputThread;

    public GameCanvas(GameFrame frame) {
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

    @Override
    public void paintComponent(Graphics g) {
        var g2d = (Graphics2D) g;
        for (DrawingObject drawingObject : drawingObjects) {
            drawingObject.draw(g2d);
        }
    }

    public void add(GameObject gameObject) {
        if (gameObject instanceof DrawingObject drawingObject)
            drawingObjects.add(drawingObject);
        if (gameObject instanceof PhysicsObject physicsObject) {
            physicsThread.add(physicsObject);
        }
        if (gameObject instanceof InputObject inputObject) {
            inputThread.add(inputObject);
        }

    }

    public void setPhysicsThread(PhysicsThread thread) {
        physicsThread = thread;
    }

    public void setInputThread(Input thread) {
        inputThread = thread;
    }

    public void ready() {

    }

}