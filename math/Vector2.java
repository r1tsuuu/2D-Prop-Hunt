package math;

public class Vector2 {

    public static final Vector2 UP = new Vector2(0, -1);
    public static final Vector2 DOWN = new Vector2(0, 1);
    public static final Vector2 LEFT = new Vector2(-1, 0);
    public static final Vector2 RIGHT = new Vector2(0, 1);
    public static final Vector2 ZERO = new Vector2(0, 0);
    public static final Vector2 ONE = new Vector2(1, 1);

    float x;
    float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
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
}
