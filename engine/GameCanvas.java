/**
	The GameCanvas is a custom JComponent that contains all the Game Objects.
    It is where Drawing Objects are drawn, and other Game Objects are processed by
    the threads that access them.

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

    /**
     * Instantiates a new GameCanvas with reference to the Game's Frame.
     * 
     * @param frame the GameFrame.
     */
    public GameCanvas(GameFrame frame) {
        setPreferredSize(new Dimension(800, 600));
        drawingObjects = new ArrayList<DrawingObject>();
        physicsObjects = new ArrayList<PhysicsObject>();
        inputObjects = new ArrayList<InputObject>();
        networkOutObjects = new ArrayList<NetworkOutObject>();
        networkInObjects = new ArrayList<NetworkInObject>();
        this.frame = frame;
    }

    /**
     * Calls each DrawingObject to process their visual information.
     * Called every frame.
     * 
     * @param delta the time between the last call/frame.
     */
    public void process(float delta) {
        for (int i = 0; i < drawingObjects.size(); i++) {
            var drawingObject = drawingObjects.get(i);
            drawingObject.process(delta);
        }
        repaint();
        revalidate();
    }

    /**
     * Overriden custom paint component.
     * 
     * @param g the Graphics Object.
     */
    @Override
    public void paintComponent(Graphics g) {
        var g2d = (Graphics2D) g;
        for (int i = 0; i < drawingObjects.size(); i++) {
            var drawingObject = drawingObjects.get(i);
            drawingObject.draw(g2d);
        }
    }

    /**
     * Adds the GameObject to the canvas and adds it to the lists
     * of objects that are accessed by the different threads when implemented
     * by their corresponding types.
     * 
     * @param gameObject the object to add.
     */
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

    /**
     * Case-to-case basis for NetworkOutObjects that are not GameObjects.
     * 
     * @param n networkOutObject.
     */
    public void addNetworkOutObject(NetworkOutObject n) {
        networkOutObjects.add(n);
    }

    /**
     * Case-to-case basis for NetworkInObjects that are not GameObjects.
     * 
     * @param n networkInObject.
     */
    public void addNetworkInObject(NetworkInObject n) {
        networkInObjects.add(n);
    }

    /**
     * Removes the object from the canvas and the list of objects accessed
     * by the threads to process.
     * 
     * @param gameObject object to remove.
     */
    public void remove(GameObject gameObject) {
        if (gameObject instanceof DrawingObject drawingObject)
            drawingObjects.remove(drawingObject);
        if (gameObject instanceof PhysicsObject physicsObject) {
            physicsObjects.remove(physicsObject);
        }
        if (gameObject instanceof InputObject inputObject) {
            inputObjects.remove(inputObject);
        }
        if (gameObject instanceof NetworkInObject networkInObject) {
            networkInObjects.remove(networkInObject);
        }
        if (gameObject instanceof NetworkOutObject networkOutObject) {
            networkOutObjects.remove(networkOutObject);
        }
    }

    /**
     * @return Returns the list of physics objects
     */
    public ArrayList<PhysicsObject> getPhysicsObjects() {
        return physicsObjects;
    }

    /**
     * @return Returns the list of input objects.
     */
    public ArrayList<InputObject> getInputObjects() {
        return inputObjects;
    }

    /**
     * @return Returns the list of network out objects.
     */
    public ArrayList<NetworkOutObject> getNetworkOutObjects() {
        return networkOutObjects;
    }

    /**
     * @return Returns the list of network in objects.
     */
    public ArrayList<NetworkInObject> getNetworkInObjects() {
        return networkInObjects;
    }

    /**
     * Overridable function called whenever the network thread receives a message
     * 
     * @param input message of the server.
     */
    public void networkNotified(String input) {

    }

    /**
     * Overridable Function called after everything is initalized and safe for
     * processing.
     */
    protected void ready() {

    }

    /**
     * returns the GameFrame
     * 
     * @retunr GameFrame.
     */
    protected GameFrame getFrame() {
        return frame;
    }

}