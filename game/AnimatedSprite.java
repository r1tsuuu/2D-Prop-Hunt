/**
	The AnimatedSprite class is a Sprite Object with added animation functionality.
    The animation is handled by the process method and overriding it disables it.
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

package game;

import math.Vector2;

public class AnimatedSprite extends Sprite {

    int fps;
    float totalTime;
    float currentTime;
    int xFrameCount;

    /**
     * Instantiates a new Animated Sprite
     * 
     * @param file        path
     * @param w           sprite width
     * @param h           sprite height
     * @param fps         speed of animation in frames per second
     * @param position    position of the animated sprite
     * @param xFrameCount number of sprites horizontally in the spritesheet
     */
    public AnimatedSprite(String file, int w, int h, int fps, Vector2 position, int xFrameCount) {
        super(file, w, h, 0, 0, position);
        this.fps = fps;
        currentTime = 0;
        totalTime = 0;
        this.xFrameCount = xFrameCount;
    }

    /**
     * Switches out the displayed sprite depending on the fps
     */
    @Override
    public void process(float delta) {
        if (fps == 0)
            return;
        if (totalTime > 1f / fps) {
            totalTime = 0;
            x += 1;
        }
        if (x >= xFrameCount) {
            x = 0;
        }
        totalTime += delta;
    }
}