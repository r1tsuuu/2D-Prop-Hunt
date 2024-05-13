/**
	The GraphicsThread class processes the visual information to be displayed to the frame
    and to the screen. It runs at the speed of the refresh rate and updates each object at said speed.
    As a seperate thread, it does not block other Game Loops.


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

package engine.drawing;

import engine.GameCanvas;

public class GraphicsThread extends Thread {

    private int refreshRate;
    private GameCanvas canvas;

    /**
     * Creates the GraphicsThread given refreshrate and the current canvas.
     * 
     * @param refreshRate the refreshrate or fps.
     * @param canvas      the starting canvas.
     */
    public GraphicsThread(int refreshRate, GameCanvas canvas) {
        this.refreshRate = refreshRate;
        this.canvas = canvas;
    }

    /**
     * Starts the Graphics Thread loop
     */
    public void run() {
        long previousTime = System.nanoTime();

        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - previousTime >= 1_000_000_000 / refreshRate) {
                canvas.process((currentTime - previousTime) / 1_000_000_000f);
                previousTime = currentTime;
                canvas.repaint();
            }
        }
    }

    /**
     * Sets the canvas to process by the Graphics Thread.
     * 
     * @param canvas the canvas to process.
     */
    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }
}
