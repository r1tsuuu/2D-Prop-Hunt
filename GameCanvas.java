import javax.swing.JComponent;
import java.awt.Dimension;
public class GameCanvas extends JComponent {
    public GameCanvas() {
        setPreferredSize(new Dimension(800, 600));
    }

    // called every frame
    public void process(float delta) {
        System.out.println(delta);
    }
}