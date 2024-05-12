package engine;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import engine.drawing.GraphicsThread;
import engine.input.Input;
import engine.network.NetworkThread;
import engine.physics.PhysicsThread;
import game.GameOver;
import game.HiderScene;
import game.Lobby;
import game.SeekerScene;

public class GameFrame extends JFrame {
    private PhysicsThread physicsThread;
    private GraphicsThread graphicsThread;
    private NetworkThread networkThread;
    private Input inputThread;
    private GameCanvas currentScene;
    private String address;
    private int port;

    public GameFrame(String title) {
        super(title);
        askNetworkDetails();
        initialize();

    }

    private void askNetworkDetails() {
        address = JOptionPane.showInputDialog(
                this, "Enter Address:", "Prop Hunt: Hide and Seek", JOptionPane.PLAIN_MESSAGE);
        if (address == null)
            System.exit(0);
        String sPort = JOptionPane.showInputDialog(this, "Enter Port:", "Prop Hunt: Hide and Seek",
                JOptionPane.PLAIN_MESSAGE);
        if (sPort == null)
            System.exit(0);
        port = Integer.parseInt(sPort);
    }

    private void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentScene = new Lobby(this);

        graphicsThread = new GraphicsThread(120, currentScene);
        physicsThread = new PhysicsThread(currentScene);
        inputThread = new Input(this, currentScene);
        networkThread = new NetworkThread(address, port, currentScene);
        networkThread.establishConnection();

        graphicsThread.start();
        physicsThread.start();
        inputThread.start();
        networkThread.start();

        add(currentScene);
        currentScene.ready();

        pack();
        setVisible(true);
        setMinimumSize(new Dimension(800, 600));
        setResizable(false);
    }

    public void startGame(String perspective) {
        if (perspective.equals("seeker"))
            setScene(new SeekerScene(this));
        else if (perspective.equals("hider"))
            setScene(new HiderScene(this));
    }

    public void endGame(String result) {
        setScene(new GameOver(this, result));
    }

    private void setScene(GameCanvas scene) {
        remove(currentScene);
        currentScene = scene;
        graphicsThread.setCanvas(currentScene);
        physicsThread.setCanvas(currentScene);
        inputThread.setCanvas(currentScene);
        networkThread.setCanvas(currentScene);
        add(currentScene);
        currentScene.ready();
    }
}
