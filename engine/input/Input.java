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

    public static class GameInput {
        public static class Key extends GameInput {
            public final static int TYPED = 0;
            public final static int PRESSED = 1;
            public final static int RELEASED = 2;

            private char player;
            private int type;

            public Key(char player, int type) {
                this.player = player;
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public char getKey() {
                return player;
            }
        }

        public static class Mouse extends GameInput {
            public final static int CLICKED = 0;
            public final static int PRESSED = 1;
            public final static int RELEASED = 2;
            public final static int ENTERED = 3;
            public final static int EXITED = 4;
            private Vector2 position;
            private int type;

            public Mouse(Vector2 position, int type) {
                this.position = position;
                this.type = type;
            }
            public Vector2 getPosition() {
                return position;
            }
            public int getType() {
                return type;
            }
        }

        public static class MouseMotion extends GameInput {
            public final static int DRAGGED = 0;
            public final static int MOVED = 1;
            private Vector2 position;
            private int type;
            public MouseMotion(Vector2 position, int type) {
                this.position = position;
                this.type = type;
            }

            public Vector2 getPosition() {
                return position;
            }

            public int getType() {
                return type;
            }
        }
    }
    private GameFrame frame;
    private GameCanvas canvas;

    public Input(GameFrame frame) {
        this.frame = frame;
    }
    public Input(GameFrame frame, GameCanvas canvas) {
        this(frame);
        this.canvas = canvas;
    }

    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }

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
                notifyObjects(new GameInput.MouseMotion(new Vector2(e.getX(), e.getY()), GameInput.MouseMotion.DRAGGED));
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


    public void notifyObjects(GameInput input) {
        var inputObjects = canvas.getInputObjects();
        for (InputObject inputObject : inputObjects) {
            inputObject.inputEvent(input);
        }
    }
}