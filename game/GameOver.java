package game;

import java.io.DataOutputStream;
import java.io.IOException;

import engine.GameCanvas;
import engine.GameFrame;
import engine.network.NetworkOutObject;

public class GameOver extends GameCanvas {

    String result;

    public GameOver(GameFrame frame, String result) {
        super(frame);
        this.result = result;
    }

    @Override
    public void ready() {
        System.out.println(result);
    }

    public void replay(boolean b) {
        var replaySender = new NetworkOutObject() {

            @Override
            public void send(DataOutputStream dataOut) {
                try {
                    dataOut.writeUTF("replay " + b);
                    System.out.println("sending replay: " + b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        addNetworkOutObject(replaySender);
        System.out.println("done");
    }

    @Override
    public void networkNotified(String input) {
        if (input.equals("hider") || input.equals("seeker"))
            getFrame().startGame(input);
        if (input.equals("STOP"))
            System.exit(0);
    }
}