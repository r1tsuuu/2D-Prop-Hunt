/**
	The Timer class displays a 3digit timer anchored to a position.
    It is not a GameObject but it is a network in object as the
    current time is received from the server.

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

public class Timer implements NetworkInObject {
    /**
     * Digit class displays a sprite given number.
     * It is used in conjunction with Timer to display the current time.
     */
    private class Digit extends Sprite {

        /**
         * Instances a new digit anchored to position and offset.
         */
        public Digit(Vector2 position, Vector2 offset) {
            super("assets\\numbers.png", 7, 10, 0, 0, position);
            setOffset(offset);
        }

        /**
         * Sets the digit didsplayed to the character digit.
         */
        public void setDigit(char digit) {
            x = Integer.parseInt(String.valueOf(digit));
        }
    }

    /**
     * Interface class that detects whenever the time is updated from the server.
     * @param time the time;
     */
    public interface TimerListener {
        public void onTimeReceived(int time);
    }

    TimerListener listener;
    Digit ones;
    Digit tens;
    Digit hund;
    float scale;

    /**
     * Returns the ones digit of the timer.
     */
    public Digit getOnes() {
        return ones;
    }

    /**
     * Returns the tens digit of the timer.
     */
    public Digit getTens() {
        return tens;
    }

    /**
     * Returns the hundredths digit of the timer.
     */
    public Digit getHund() {
        return hund;
    }

    /**
     * Instantiates a new Timer on the position with set offsets for the three digits
     * @param position position.
     */
    public Timer(Vector2 position) {
        hund = new Digit(position, new Vector2(0, -10));
        tens = new Digit(position, new Vector2(7, -10));
        ones = new Digit(position, new Vector2(14, -10));
    }

    /**
     * Receives the message from the server.
     */
    @Override
    public void receive(String input) {
        if (input.charAt(0) == 't') {
            int value = Integer.parseInt(input.split(" ")[1]);
            String formatted = String.format("%03d", value);
            hund.setDigit(formatted.charAt(0));
            tens.setDigit(formatted.charAt(1));
            ones.setDigit(formatted.charAt(2));
            if (listener != null)
                listener.onTimeReceived(value);
        }
    }

    /**
     * Sets the timerlistener to the current new listener.
     * @param listener to listen to.
     */
    public void setListener(TimerListener listener) {
        this.listener = listener;
    }
}
