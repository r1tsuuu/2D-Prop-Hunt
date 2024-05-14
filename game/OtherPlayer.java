/**
	Other player class is a simple object that matches the position and image of the 
    opposing player. Data about the opposing player is sent through the server. Essentially
    a puppet controlled through the internet.

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

import engine.network.NetworkInObject;
import math.Vector2;

public class OtherPlayer extends AnimatedSprite implements NetworkInObject {
    String type;

    /**
     * Instantiates the other player.
     */
    public OtherPlayer(String name, Vector2 position, int w, int h, String path, int fps, int xFrameCount) {
        super(path, w, h, 0, position, xFrameCount);
    }

    /**
     * Receives the data from the server, if 'p' it contains only positional data
     * if 's', it means the data came from a seeker, with additional value for the
     * current x frame of the animation of the seeker.
     */
    @Override
    public void receive(String input) {
        if (input.charAt(0) == 'p' || input.charAt(0) == 's') {
            var result = input.split(" ");
            var newPos = new Vector2(result[1], result[2]);
            x = Integer.parseInt(result[3]);
            if (input.charAt(0) == 's') {
                y = Integer.parseInt(result[4]);
            }
            getPosition().set(newPos);
        }
    }

}
