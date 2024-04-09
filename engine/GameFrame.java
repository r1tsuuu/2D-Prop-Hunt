package engine;
import javax.swing.JFrame;

import game.SampleScene;
public class GameFrame extends JFrame {
    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameCanvas canvas = new SampleScene();
        GraphicsThread gthread = new GraphicsThread(60, canvas);
        add(canvas);
        gthread.start();
        pack();
        setVisible(true);
    }
}
