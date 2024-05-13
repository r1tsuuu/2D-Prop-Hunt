/**
	The DrawingObject interface provides the blueprint for objects that will be displayed in the
    frame. The process method is called at the speed of the set refresh rate. The draw is called after the object has processed its current frame.

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

import java.awt.Graphics2D;

public interface DrawingObject {

    /**
     * Called at refresh rate times per second.
     * 
     * @param delta the time between the last call.
     */
    public void process(float delta);

    /**
     * Dictates how the DrawingObject will be drawn on the scene.
     * 
     * @param g2d Graphics2D object.
     */
    public void draw(Graphics2D g2d);
}
