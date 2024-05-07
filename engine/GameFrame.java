package engine;

import javax.swing.JFrame;

import engine.drawing.GraphicsThread;
import engine.input.Input;
import engine.network.NetworkThread;
import engine.physics.PhysicsThread;
import game.Lobby;
import game.SampleScene;

public class GameFrame extends JFrame {
    private PhysicsThread physicsThread;
    private GraphicsThread graphicsThread;
    private NetworkThread networkThread;
    private Input inputThread;
    private GameCanvas currentScene;

    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentScene = new Lobby(this);

        graphicsThread = new GraphicsThread(120, currentScene);
        physicsThread = new PhysicsThread(currentScene);
        inputThread = new Input(this, currentScene);
        networkThread = new NetworkThread("localhost", 4952, currentScene);
        networkThread.establishConnection();
        
        add(currentScene);
        currentScene.ready();

        graphicsThread.start();
        physicsThread.start();
        inputThread.start();
        networkThread.start();
        pack();
        setVisible(true);
    }

    public void startGame() {
        remove(currentScene);
        currentScene = new SampleScene(this);
        graphicsThread.setCanvas(currentScene);
        physicsThread.setCanvas(currentScene);
        inputThread.setCanvas(currentScene);
        networkThread.setCanvas(currentScene);
        add(currentScene);
        currentScene.ready();
    }
}
