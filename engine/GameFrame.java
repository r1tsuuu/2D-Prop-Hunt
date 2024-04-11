package engine;

import javax.swing.JFrame;

import engine.drawing.GraphicsThread;
import engine.input.Input;
import engine.physics.PhysicsThread;
import game.SampleScene;

public class GameFrame extends JFrame {
    private PhysicsThread physicsThread;
    private GraphicsThread graphicsThread;
    private Input inputThread;

    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameCanvas canvas = new SampleScene(this);

        graphicsThread = new GraphicsThread(60, canvas);
        physicsThread = new PhysicsThread();
        inputThread = new Input(this);
        add(canvas);

        graphicsThread.start();
        physicsThread.start();
        inputThread.start();

        canvas.setPhysicsThread(physicsThread);
        canvas.setInputThread(inputThread);

        canvas.ready();

        pack();
        setVisible(true);
    }
}
