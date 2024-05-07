package engine;

import javax.swing.JComponent;

import engine.drawing.DrawingObject;
import engine.input.InputObject;
import engine.network.NetworkInObject;
import engine.network.NetworkOutObject;
import engine.physics.PhysicsObject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameCanvas extends JComponent {

    private ArrayList<DrawingObject> drawingObjects;
    private ArrayList<PhysicsObject> physicsObjects;
    private ArrayList<InputObject> inputObjects;
    private ArrayList<NetworkOutObject> networkOutObjects;
    private ArrayList<NetworkInObject> networkInObjects;
    private GameFrame frame;
    public GameCanvas(GameFrame frame) {
        setPreferredSize(new Dimension(800, 600));
        drawingObjects = new ArrayList<DrawingObject>();
        physicsObjects = new ArrayList<PhysicsObject>();
        inputObjects = new ArrayList<InputObject>();
        networkOutObjects = new ArrayList<NetworkOutObject>();
        networkInObjects = new ArrayList<NetworkInObject>();
        this.frame = frame;
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
            physicsObjects.add(physicsObject);
        }
        if (gameObject instanceof InputObject inputObject) {
            inputObjects.add(inputObject);
        }
        if (gameObject instanceof NetworkInObject networkInObject) {
            networkInObjects.add(networkInObject);
        }
        if (gameObject instanceof NetworkOutObject networkOutObject) {
            networkOutObjects.add(networkOutObject);
        }

    }

    public ArrayList<PhysicsObject> getPhysicsObjects() {
        return physicsObjects;
    }

    public ArrayList<InputObject> getInputObjects() {
        return inputObjects;
    }

    public ArrayList<NetworkOutObject> getNetworkOutObjects() {
        return networkOutObjects;
    }

    public ArrayList<NetworkInObject> getNetworkInObjects() {
        return networkInObjects;
    }

    public void networkNotified(String input) {

    }

    protected void ready() {

    }

    protected GameFrame getFrame() {
        return frame;
    }

}