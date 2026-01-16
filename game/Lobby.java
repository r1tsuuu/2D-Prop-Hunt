/**
	Lobby Scene is the first scene loaded. It waits until all the players
    in the server are full (2 players)

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

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import math.Vector2;

public class Lobby extends GameCanvas {

    private ImageObject lobbyImage;

    /**
     * Instantiates the lobby
     */
    public Lobby(GameFrame frame) {
        super(frame);
        lobbyImage = new ImageObject("Lobby", Vector2.ZERO, "assets/LobbyScreens/Lobby.png");
    }

    /**
     * Adds the background image.
     */
    @Override
    public void ready() {
        add(lobbyImage);
    }

    /**
     * receives messages from the server.
     * notifies the frame when the game should start
     */
    @Override
    public void networkNotified(String input) {
        if (input.equals("hider")) {
            getFrame().startGame(input);
        } else if (input.equals("wait")) {
            remove(lobbyImage);
            add(new ImageObject("wait", Vector2.ZERO, "assets/LobbyScreens/SeekerBlindfold.png"));
        } else if (input.equals("seeker")) {
            getFrame().startGame(input);
        }

    }

}
