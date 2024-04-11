package engine.drawing;

import engine.GameCanvas;

public class GraphicsThread extends Thread {

    private boolean running;
    private boolean paused;
    private int refreshRate;
    private GameCanvas canvas;

    public GraphicsThread(int refreshRate, GameCanvas canvas) {
        this.refreshRate = refreshRate;
        this.canvas = canvas;
        paused = false;
    }

    public void run() {
        running = true;
        paused = false;
        long previousTime = System.nanoTime();
        
        while (running) {
            while (paused) {}
            long currentTime = System.nanoTime();
            if (currentTime - previousTime >= 1_000_000_000 / refreshRate) {
                canvas.process((currentTime - previousTime) / 1_000_000_000f);
                previousTime = currentTime;
                canvas.repaint();
            }
        }
        System.out.println("done!");
    }

    public void pauseGraphics() {
        paused = true;
    }

    public void resumeGraphics() {
        paused = false;
    }

    public void stopGraphics() {
        running = false;
        paused = false;
    }
}
