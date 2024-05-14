/**
	The GameFrame is the Game window of the Game.
    It receives input which is detected by the network thread and contains
    the GameCanas that displays visual information.

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

import java.awt.Dimension;
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

    /**
     * Instantiates a new GameFrame with title
     * 
     * @param title title of the window.
     */
    public GameFrame(String title) {
        super(title);
        askNetworkDetails();
        initialize();
    }

    /**
     * Displays a JOptionPane that asks for the server address and port.
     */
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

    /**
     * Initializes the threads and the default scene.
     */
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

    /**
     * Called to start the game. Switches the scene to the corresponding chosen
     * perspective.
     * 
     * @param perspective of the player: ie hider or seeker.
     */
    public void startGame(String perspective) {
        if (perspective.equals("seeker"))
            setScene(new SeekerScene(this));
        else if (perspective.equals("hider"))
            setScene(new HiderScene(this));
    }

    /**
     * Called to end the round. Switches the scene to the GameOver scene.
     * 
     * @param result of the previous round.
     */
    public void endGame(String result) {
        var gameOverScene = new GameOver(this, result);
        setScene(gameOverScene);
        try {
            // letting the result seep in
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        int replay = JOptionPane.showConfirmDialog(this, "Play Again?", "Prop Hunt: Hide and Seek",
                JOptionPane.YES_NO_OPTION);

        if (replay == JOptionPane.YES_OPTION) {
            gameOverScene.replay(true);
        } else {
            gameOverScene.replay(false);
        }
    }

    /**
     * Sets the current scene to the selected scene
     * 
     * @param scene the new scene to display and process.
     */
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
