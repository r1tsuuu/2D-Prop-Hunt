package game;

import engine.network.NetworkInObject;
import math.Vector2;

public class Timer implements NetworkInObject {
    private class Digit extends Sprite {

        public Digit(Vector2 position, Vector2 offset) {
            super("assets\\numbers.png", 7, 10, 0, 0, position);
            setOffset(offset);
        }

        public void setDigit(char digit) {
            x = Integer.parseInt(String.valueOf(digit));
        }
    }

    Digit ones;
    Digit tens;
    Digit hund;
    float scale;

    public Digit getOnes() {
        return ones;
    }

    public Digit getTens() {
        return tens;
    }

    public Digit getHund() {
        return hund;
    }

    public Timer(Vector2 position) {
        hund = new Digit(position, new Vector2(0, -10));
        tens = new Digit(position, new Vector2(7, -10));
        ones = new Digit(position, new Vector2(14, -10));
    }

    @Override
    public void receive(String input) {
        if (input.charAt(0) == 't') {
            int value = Integer.parseInt(input.split(" ")[1]);
            String formatted = String.format("%03d", value);
            hund.setDigit(formatted.charAt(0));
            tens.setDigit(formatted.charAt(1));
            ones.setDigit(formatted.charAt(2));
        }
    }
}
