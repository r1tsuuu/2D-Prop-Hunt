/**
	The Input classes streamlines and simplifies Input handling of the Game.
    Initially a thread, the Input class handles all input received  by the 
    Game frame and notifies all instantiated InputObjects.

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

package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import engine.GameCanvas;
import engine.GameFrame;
import math.Vector2;

public class Input {

    /**
     * The GameInput is the baseclass for different kinds of input.
     * Each instantiated GameInput is sent to all instantiated InputObjects
     **/
    public static class GameInput {

        /**
         * The Key class is a type of GameInput that contains keyboard input data.
         * The input data has three types: TYPED, PRESSED, and RELEASED.
         */
        public static class Key extends GameInput {
            public final static int TYPED = 0;
            public final static int PRESSED = 1;
            public final static int RELEASED = 2;

            private char key;
            private int type;

            /**
             * Instantiates a new Key GameInput.
             * 
             * @param key  character used in the input
             * @param type type of input the character is related to.
             */
            public Key(char key, int type) {
                this.key = key;
                this.type = type;
            }

            /**
             * Returns the type of input
             * 
             * @return type.
             */
            public int getType() {
                return type;
            }

            /**
             * @retuns the key used in the input
             * @return key.
             */
            public char getKey() {
                return key;
            }
        }

        /**
         * The Mouse class is a type of GameInput that contains mouse button input data
         * The input data has five types: CLICKED, PRESSED, RELEAESD, ENTERED, and
         * EXITED.
         */
        public static class Mouse extends GameInput {
            public final static int CLICKED = 0;
            public final static int PRESSED = 1;
            public final static int RELEASED = 2;
            public final static int ENTERED = 3;
            public final static int EXITED = 4;
            private Vector2 position;
            private int type;

            /**
             * Creates a new Mouse Game input.
             * 
             * @param position position of the mouse during input.
             * @param type     type of Mouse button input.
             */
            public Mouse(Vector2 position, int type) {
                this.position = position;
                this.type = type;
            }

            /**
             * Returns the position of the mouse button input.
             * 
             * @return position.
             */
            public Vector2 getPosition() {
                return position;
            }

            /**
             * Returns the type of mouse bututon input.
             * 
             * @return type.
             */
            public int getType() {
                return type;
            }
        }

        /**
         * The MouseMotion class is a type of GameInput that contains mouse motion input
         * data.
         * The input data has two types: DRAGGED, and MOVED.
         */
        public static class MouseMotion extends GameInput {
            public final static int DRAGGED = 0;
            public final static int MOVED = 1;
            private Vector2 position;
            private int type;

            /**
             * Instantiates a new Mouse Motion Game Input from position and type.
             * 
             * @param position position of the Mouse motion input
             * @param type     type of mouse motion input.
             */
            public MouseMotion(Vector2 position, int type) {
                this.position = position;
                this.type = type;
            }

            /**
             * Returns the position of the mouse motion input.
             * 
             * @return position.
             */
            public Vector2 getPosition() {
                return position;
            }

            /**
             * Returns the type of the mouse motion input.
             * 
             * @return type.
             */
            public int getType() {
                return type;
            }
        }
    }

    private GameFrame frame;
    private GameCanvas canvas;

    /**
     * Instantiates the Input Handler given frame and canvas.
     * 
     * @param frame  frame that accepts input.
     * @param canvas canvas that contains the input objects to notify.
     */
    public Input(GameFrame frame, GameCanvas canvas) {
        this.frame = frame;
        this.canvas = canvas;
    }

    /**
     * Sets the canvas that contains the list of InpuObjects to notify during input
     * events.
     * 
     * @param canvas canvas.
     */
    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Initializes the individual listeners that each listen for events.
     */
    public void start() {
        KeyListener keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                notifyObjects(new GameInput.Key(e.getKeyChar(), GameInput.Key.TYPED));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                notifyObjects(new GameInput.Key(e.getKeyChar(), GameInput.Key.PRESSED));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                notifyObjects(new GameInput.Key(e.getKeyChar(), GameInput.Key.RELEASED));
            }

        };
        MouseListener mouseListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                notifyObjects(new GameInput.Mouse(new Vector2(e.getX(), e.getY()), GameInput.Mouse.CLICKED));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                notifyObjects(new GameInput.Mouse(new Vector2(e.getX(), e.getY()), GameInput.Mouse.PRESSED));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                notifyObjects(new GameInput.Mouse(new Vector2(e.getX(), e.getY()), GameInput.Mouse.RELEASED));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                notifyObjects(new GameInput.Mouse(new Vector2(e.getX(), e.getY()), GameInput.Mouse.ENTERED));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                notifyObjects(new GameInput.Mouse(new Vector2(e.getX(), e.getY()), GameInput.Mouse.EXITED));
            }

        };
        MouseMotionListener mouseMotionListener = new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                notifyObjects(
                        new GameInput.MouseMotion(new Vector2(e.getX(), e.getY()), GameInput.MouseMotion.DRAGGED));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                notifyObjects(new GameInput.MouseMotion(new Vector2(e.getX(), e.getY()), GameInput.MouseMotion.MOVED));
            }

        };
        frame.addKeyListener(keyListener);
        frame.addMouseListener(mouseListener);
        frame.addMouseMotionListener(mouseMotionListener);
    }

    /**
     * Notifies theh Input Objects of new input data.
     * 
     * @param input the new input data.
     */
    public void notifyObjects(GameInput input) {
        var inputObjects = canvas.getInputObjects();
        for (InputObject inputObject : inputObjects) {
            inputObject.inputEvent(input);
        }
    }
}