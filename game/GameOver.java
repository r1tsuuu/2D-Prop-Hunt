/**
	The GameOver class displays the result to the clients.
    Depending on the outcome sent by the server. It displays whether
    the player won or lost.

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

package game;

import java.io.DataOutputStream;
import java.io.IOException;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import engine.network.NetworkOutObject;
import math.Vector2;

public class GameOver extends GameCanvas {

    String result;
    private ImageObject imageResult;

    /**
     * Instantiates the GameOver screen
     * 
     * @param frame  the game frame
     * @param result the result
     */
    public GameOver(GameFrame frame, String result) {
        super(frame);
        this.result = result;
    }

    /**
     * Displays the corresponding result image.
     */
    @Override
    public void ready() {
        if (result.equals("victory"))
            imageResult = new ImageObject("screen", Vector2.ZERO, "assets/LobbyScreens/WinScreen.png");
        else if (result.equals("defeat"))
            imageResult = new ImageObject("screen", Vector2.ZERO, "assets/LobbyScreens/LoseScreen.png");
        add(imageResult);
    }

    /**
     * Sends the server the result whether the player wants to play another round.
     */
    public void replay(boolean b) {
        var replaySender = new NetworkOutObject() {

            @Override
            public void send(DataOutputStream dataOut) {
                try {
                    dataOut.writeUTF("replay " + b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        addNetworkOutObject(replaySender);
    }

    /**
     * Awaits input from the server. Starts the game if new role is received. Adds
     * the wait image when. It is told to wait. Exits when at least one player
     * declines to play again.
     */
    @Override
    public void networkNotified(String input) {
        if (input.equals("hider") || input.equals("seeker"))
            getFrame().startGame(input);
        if (input.equals("wait")) {
            remove(imageResult);
            add(new ImageObject("Lobby", Vector2.ZERO, "assets/LobbyScreens/SeekerBlindfold.png"));
        }
        if (input.equals("STOP"))
            System.exit(0);
    }
}
