package game;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import math.Vector2;

public class Lobby extends GameCanvas {

    public Lobby(GameFrame frame) {
        super(frame);
    }

    @Override
    public void ready() {
        add(new ImageObject("Lobby", Vector2.ZERO, "assets\\LobbyScreens\\Lobby.png"));
    }

    @Override
    public void networkNotified(String input) {
        if (input.equals("seeker") || input.equals("hider")) {
            getFrame().startGame(input);
        }
    }

}
