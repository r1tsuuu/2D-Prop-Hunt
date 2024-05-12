package game;

import engine.GameCanvas;
import engine.GameFrame;

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
    
}
