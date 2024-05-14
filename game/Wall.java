/**
	The Wall class instantiates a static wall that prevents Player classes from moving to it.
    It accepts two inputs, the start position of the rectangle and the end position of the rectangle.

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

import engine.GameObject;
import engine.physics.CollisionBox;
import engine.physics.PhysicsObject;
import math.Vector2;

public class Wall extends GameObject implements PhysicsObject {

    private Vector2 size;

    /**
     * Instantiates a wall given start and end positions that form a rectangle.
     */
    public Wall(Vector2 position, Vector2 endPosition) {
        size = Vector2.add(endPosition, Vector2.multiply(position, -1));
        new CollisionBox(this, position, size, 0);
    }

    /**
     * Required function when implementing Physics object
     */
    @Override
    public void physicsProcess(float delta) {
    }

    /**
     * invoked when collided with another PhysicsObject.
     */
    @Override
    public void collided(PhysicsObject other, String direction, float offset) {
    }

}
