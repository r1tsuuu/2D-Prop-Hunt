package math;

import java.awt.Dimension;

public class Vector2 {

    public static final Vector2 UP = new Vector2(0, -1);
    public static final Vector2 DOWN = new Vector2(0, 1);
    public static final Vector2 LEFT = new Vector2(-1, 0);
    public static final Vector2 RIGHT = new Vector2(1, 0);
    public static final Vector2 ZERO = new Vector2(0, 0);
    public static final Vector2 ONE = new Vector2(1, 1);

    float x;
    float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 old) {
        this.x = old.x;
        this.y = old.y;
    }

    public Vector2(Dimension d) {
        this.x = (float) d.getWidth();
        this.y = (float) d.getHeight();
    }

    public Vector2(float angle) {
        x = (float) Math.cos(angle);
        y = (float) Math.sin(angle);
    }

    public void set(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector2(String string) {
        var values = string.split(" ");
        x = Float.parseFloat(values[0]);
        y = Float.parseFloat(values[1]);
    }

    public Vector2(String x, String y) {
        this(x + " " + y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Adds the other vector to the current vector
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
     * Returns the sum of two vectors
     */
    public static Vector2 add(Vector2 vone, Vector2 vtwo) {
        return new Vector2(vone.x + vtwo.x, vone.y + vtwo.y);
    }

    public Vector2 multiply(float n) {
        this.x *= n;
        this.y *= n;
        return this;
    }

    public static Vector2 multiply(Vector2 v, float n) {
        return new Vector2(v.x * n, v.y * n);
    }

    public static boolean intersect(Vector2 starta, Vector2 enda, Vector2 startb, Vector2 endb) {
        return intersect(starta.getX(), enda.getX(), startb.getX(), endb.getX()) &&
                intersect(starta.getY(), enda.getY(), startb.getY(), endb.getY());
    }

    private static boolean intersect(float starta, float enda, float startb, float endb) {
        return starta < endb && startb < enda;
    }

    public static Vector2 lerp(Vector2 a, Vector2 b, float i) {
        float x = a.x + (b.x - a.x) * i;
        float y = a.y + (b.y - a.y) * i;
        return new Vector2(x, y);
    }

    public static float getDistance(Vector2 a, Vector2 b) {
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y-b.y, 2));
    }

    public float getAngle() {
        return (float) Math.atan2(y, x);
    }

    public static boolean ccw(Vector2 a, Vector2 b, Vector2 c) {
        return (c.y - a.y) * (b.x - a.x) > (b.y - a.y) * (c.x - a.x);
    }

    public static boolean lineIntersectsLine(Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
        return ccw(a, c, d) != ccw(b, c, d) && ccw(a, b, c) != ccw(a, b, d);
    }

    public static boolean lineIntersectsRectangle(Vector2 a, Vector2 b, Vector2 rect, Vector2 size) {
        var end = Vector2.add(rect, size);
        boolean left = lineIntersectsLine(a, b, rect, new Vector2(rect.x, end.y));
        boolean right = lineIntersectsLine(a, b, new Vector2(end.x, rect.y), end);
        boolean top = lineIntersectsLine(a, b, rect, new Vector2(end.x, rect.y));
        boolean bot = lineIntersectsLine(a, b, new Vector2(rect.x, end.y), end);

        return left || right || top || bot;
    }

    @Override
    public String toString() {
        return String.format("%.5f %.5f", x, y);
    }

    public boolean equals(Vector2 vector) {
        return x == vector.x && y == vector.y;
    }
}
