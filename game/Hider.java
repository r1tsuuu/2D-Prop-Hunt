/**
	Hider class represents the Player as a hider.
    Gives the ability to change the disguise as well as spawn
    Fake furniture that matches the current disguise

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
import engine.input.Input.GameInput;
import engine.network.NetworkInObject;
import math.Vector2;

public class Hider extends Player implements NetworkInObject{

    /**
     * Hider listener that is notifed when props are spawned.
     */
    public interface HiderListener {
        void onPropSpawn(Sprite prop);
    }

    private boolean sendShot;

    private int propCount;
    private HiderListener listener;
    private boolean newPropAdded;
    private Vector2 mostRecentFakePropPosition;
    private boolean canAddFakeProps;

    /**
     * Instantiates the hider
     * 
     * @param name
     * @param w
     * @param h
     * @param fps
     * @param position
     * @param path
     * @param xFrameCount
     */
    public Hider(String name, int w, int h, int fps, Vector2 position, String path, int xFrameCount) {
        super(name, w, h, fps, position, path, xFrameCount);
        sendShot = false;
        propCount = xFrameCount;
        newPropAdded = false;
        canAddFakeProps = false;
    }

    /**
     * Picks a random x value corresponding to the current spritesheet sprite.
     */
    public void changeProp() {
        int prev = x;
        while (x == prev)
            x = (int) (Math.random() * propCount);
    }

    /**
     * Spawns a sprite at the current position that matches the current spritesheet
     * sprite
     */
    public void spawnProp() {
        if (!canAddFakeProps)
            return;
        Sprite prop = new Sprite("assets\\props.png", w, h, x, 0, new Vector2(getPosition()));
        mostRecentFakePropPosition = prop.getPosition();
        if (listener != null) {
            listener.onPropSpawn(prop);
        }
        newPropAdded = true;
    }

    /**
     * Detects when f and t are pressed
     */
    @Override
    public void inputEvent(GameInput input) {
        super.inputEvent(input);
        if (input instanceof GameInput.Key inputKey) {
            if (inputKey.getKey() == 'f' && inputKey.getType() == GameInput.Key.PRESSED) {
                changeProp();
            }
            if (inputKey.getKey() == 't' && inputKey.getType() == GameInput.Key.RELEASED) {
                spawnProp();
            }
        }
    }

    /**
     * Sets the Listener object that is notified when the prop is spawned
     */
    public void setListener(HiderListener listener) {
        this.listener = listener;
    }

    /**
     * Go signal for sending to the server confirmationi of being shot.
     */
    public void shot() {
        sendShot = true;
    }

    /**
     * Sends data to the server when shot or added a new prop
     */
    @Override
    public void send(DataOutputStream dataOut) {
        try {
            dataOut.writeUTF("p " + getPosition().toString() + " " + x);
            if (sendShot) {
                dataOut.writeUTF("winSeeker");
                sendShot = false;
            }
            if (newPropAdded) {
                dataOut.writeUTF("d " + mostRecentFakePropPosition + " " + x);
                newPropAdded = false;
            }
        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }

    /**
     * Give the hider the ability to place fake props when the seeker is now moving
     */
    @Override
    public void receive(String input) {
        if (input.charAt(0) == 's') {
            canAddFakeProps = true;
        }
    }
}