import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameCanvas canvas = new GameCanvas();
        GraphicsThread gthread = new GraphicsThread(60, canvas);
        add(canvas);
        gthread.start();
        pack();
        setVisible(true);
    }
}
