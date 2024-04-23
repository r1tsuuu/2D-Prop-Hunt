package engine;

import javax.swing.JFrame;

import engine.drawing.GraphicsThread;
import engine.input.Input;
import engine.network.NetworkThread;
import engine.physics.PhysicsThread;
import game.SampleScene;

public class GameFrame extends JFrame {
    private PhysicsThread physicsThread;
    private GraphicsThread graphicsThread;
    private NetworkThread networkThread;
    private Input inputThread;

    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameCanvas canvas = new SampleScene(this);

        graphicsThread = new GraphicsThread(120, canvas);
        physicsThread = new PhysicsThread();
        inputThread = new Input(this);
        networkThread = new NetworkThread("localhost", 4952);
        add(canvas);

        graphicsThread.start();
        physicsThread.start();
        inputThread.start();
        networkThread.start();

        canvas.setPhysicsThread(physicsThread);
        canvas.setInputThread(inputThread);

        canvas.ready();

        pack();
        setVisible(true);
    }
}
