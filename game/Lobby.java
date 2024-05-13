package game;

import engine.GameCanvas;
import engine.GameFrame;
import engine.drawing.ImageObject;
import math.Vector2;

public class Lobby extends GameCanvas {

    private ImageObject lobbyImage;

    public Lobby(GameFrame frame) {
        super(frame);
        lobbyImage = new ImageObject("Lobby", Vector2.ZERO, "assets\\LobbyScreens\\Lobby.png");
    }

    @Override
    public void ready() {
        add(lobbyImage);
    }

    @Override
    public void networkNotified(String input) {
        if (input.equals("hider")) {
            getFrame().startGame(input);
        } else if (input.equals("wait")) {
            remove(lobbyImage);
            add(new ImageObject("wait", Vector2.ZERO, "assets\\LobbyScreens\\SeekerBlindfold.png"));
            System.out.println("adding wait");
        } else if (input.equals("seeker")) {
            getFrame().startGame(input);
        }

    }

}
