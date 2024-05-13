/**
	The Vector2 class provides basic vector functionality for the Game's Math
    and Physics. Provides basic vector math like algebra and linear interpolation
    as well as trigonometry.

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
package math;

import java.awt.Dimension;

public class Vector2 {

    public static final Vector2 UP = new Vector2(0, -1);
    public static final Vector2 DOWN = new Vector2(0, 1);
    public static final Vector2 LEFT = new Vector2(-1, 0);
    public static final Vector2 RIGHT = new Vector2(1, 0);
    public static final Vector2 ZERO = new Vector2(0, 0);

    float x;
    float y;

    /**
     * Creates a new vector from values
     * 
     * @param x value
     * @param y value
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new vector with the same values as the old vector
     * 
     * @param old vector
     */
    public Vector2(Vector2 old) {
        this.x = old.x;
        this.y = old.y;
    }

    /**
     * Instantiates a new vector with the same values as the Dimension object
     * 
     * @param d Dimension object
     */
    public Vector2(Dimension d) {
        this.x = (float) d.getWidth();
        this.y = (float) d.getHeight();
    }

    /**
     * Instantiates a new vector from the unit circle given angle.
     * 
     * @param angle angle of the vector in the unit circle.
     */
    public Vector2(float angle) {
        x = (float) Math.cos(angle);
        y = (float) Math.sin(angle);
    }

    /**
     * Instantiates a new vector from a string
     * 
     * @param string input
     */
    public Vector2(String string) {
        var values = string.split(" ");
        x = Float.parseFloat(values[0]);
        y = Float.parseFloat(values[1]);
    }

    /**
     * Instantiates a new vector given string values
     * 
     * @param x string value
     * @param y string value
     */
    public Vector2(String x, String y) {
        this(x + " " + y);
    }

    /**
     * Set function that preserves the reference of the vector.
     * 
     * @param vector the values of the vector to set the current vector to.
     */
    public void set(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    /**
     * Returns the x value of the vector.
     * 
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * sets the x value of the vector to the new value.
     * 
     * @param x new value.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the y value of the vector.
     * 
     * @return y;
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y value of the vector to the new value.
     * 
     * @param x new value.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Adds the other vector to the current vector.
     * Mutates the current vector.
     * 
     * @param other vector
     * @return sum
     */
    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    /**
     * Returns the sum of two vectors as a new vector.
     * 
     * @param vone the first vector.
     * @param vtwo the second vector.
     * @return sum.
     */
    public static Vector2 add(Vector2 vone, Vector2 vtwo) {
        return new Vector2(vone.x + vtwo.x, vone.y + vtwo.y);
    }

    /**
     * Returns the new vector multiplied by n.
     * 
     * @param n the value to multiply the vector by.
     * @return the vector;
     */
    public Vector2 multiply(float n) {
        this.x *= n;
        this.y *= n;
        return this;
    }

    /**
     * Returns a new vector as a product of a vector and n.
     * 
     * @param v the vector
     * @param n the multiplier
     * @return the resulting multiplied vector.
     */
    public static Vector2 multiply(Vector2 v, float n) {
        return new Vector2(v.x * n, v.y * n);
    }

    /**
     * Checks if two rectangles are intersecting.
     * 
     * @param starta the starting point of the first rectangle.
     * @param enda   the ending point of the first rectangle.
     * @param startb the starting point of the second rectangle.
     * @param endb   the ending point of the second rectangle.
     * @return true if the two rectangles intersect.
     */
    public static boolean intersect(Vector2 starta, Vector2 enda, Vector2 startb, Vector2 endb) {
        return intersect(starta.getX(), enda.getX(), startb.getX(), endb.getX()) &&
                intersect(starta.getY(), enda.getY(), startb.getY(), endb.getY());
    }

    /**
     * Checks if intervals intersect.
     * 
     * @param starta the beginning of the first interval.
     * @param enda   the end of the first interval
     * @param startb the beginning of the second interval.
     * @param endb   the end of the second interval
     * @return true if intersecting
     */
    private static boolean intersect(float starta, float enda, float startb, float endb) {
        return starta < endb && startb < enda;
    }

    /**
     * Returns the resulting vector from linear interpolation of two vectors set by
     * i.
     * When i = 0, the resulting vector is the first vector, When i = 1, the
     * resulting vector
     * is the second vector. When i = 0.5, the resulting vector is the Midpoint
     * vector between the two.
     * 
     * @param a first vector.
     * @param b second vector.
     * @param i i.
     * @return the resulting vector.
     */
    public static Vector2 lerp(Vector2 a, Vector2 b, float i) {
        float x = a.x + (b.x - a.x) * i;
        float y = a.y + (b.y - a.y) * i;
        return new Vector2(x, y);
    }

    /**
     * Returns the distance between two vectors using the distance formula.
     * 
     * @return distance between two vectors.
     */
    public static float getDistance(Vector2 a, Vector2 b) {
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    /**
     * Returns the angle of the vector.
     * 
     * @return angle.
     */
    public float getAngle() {
        return (float) Math.atan2(y, x);
    }

    /**
     * Returns true if the three points are listed in counterclockwise order
     * Taken from Bryce Boe's website and used to check for lines intersecting.
     * 
     * @param a first point.
     * @param b second point.
     * @param c third point.
     * @return true if the three points are listed in counterclockwise order
     * @see https://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/
     */
    public static boolean ccw(Vector2 a, Vector2 b, Vector2 c) {
        return (c.y - a.y) * (b.x - a.x) > (b.y - a.y) * (c.x - a.x);
    }

    /**
     * Checks if two lines are intersecting.
     * Taken from Bryce Boe's website and used to check for intersecting lines.
     * "Think of two line segments AB, and CD. These intersect if and only if points
     * A and B are separated by segment CD and points C and D are separated by
     * segment AB. If points A and B are separated by segment CD then ACD and BCD
     * should have opposite orientation meaning either ACD or BCD is
     * counterclockwise but not both." (Boe, 2006)
     * 
     * @param a point in line AB
     * @param b point in line AB
     * @param c point in line CD
     * @param d point in line CD
     * @return if two lines intersect.
     * @see https://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/
     */
    public static boolean lineIntersectsLine(Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
        return ccw(a, c, d) != ccw(b, c, d) && ccw(a, b, c) != ccw(a, b, d);
    }

    /**
     * Checks if the line intersects the rectangle.
     * Uses Bryce Boe's algorithm for checking if lines intersects the rectangle.
     * Individually checks for each side of the rectangle to see if it intersects
     * the line.
     * 
     * @param a    point in line AB that may intersect with the rectangle.
     * @param b    point in line AB that may intersect with the rectangle.
     * @param rect position of the topleft corner of the rectangle.
     * @param size the size of the rectangle.
     * @return true if line intersects the rectangle.
     * @see https://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/
     */
    public static boolean lineIntersectsRectangle(Vector2 a, Vector2 b, Vector2 rect, Vector2 size) {
        var end = Vector2.add(rect, size);
        boolean left = lineIntersectsLine(a, b, rect, new Vector2(rect.x, end.y));
        boolean right = lineIntersectsLine(a, b, new Vector2(end.x, rect.y), end);
        boolean top = lineIntersectsLine(a, b, rect, new Vector2(end.x, rect.y));
        boolean bot = lineIntersectsLine(a, b, new Vector2(rect.x, end.y), end);

        return left || right || top || bot;
    }

    /**
     * Converts the vector into String format "x.5f y.5f"
     * 
     * @return formatted string.
     */
    @Override
    public String toString() {
        return String.format("%.5f %.5f", x, y);
    }

    /**
     * checks if the vector is equal to the current vector.
     * 
     * @param vector the other vector.
     * @return true if equal.
     */
    public boolean equals(Vector2 vector) {
        return x == vector.x && y == vector.y;
    }
}
